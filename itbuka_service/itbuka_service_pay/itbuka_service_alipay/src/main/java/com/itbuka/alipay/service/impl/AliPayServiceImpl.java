package com.itbuka.alipay.service.impl;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

import com.itbuka.alipay.config.AliPayConfig;
import com.itbuka.alipay.domain.AliPay;

import com.itbuka.alipay.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AliPayServiceImpl implements AliPayService {
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private AliPayConfig aliPayConfig;
    @Autowired
    private RedisTemplate redisTemplate;

    //h5支付
    @Override
    public String H5Pay(AliPay aliPay) {
        //生成支付订单号
        Object o = redisTemplate.opsForValue().get("pay_" + aliPay.getOutTradeNo());
        if (o != null) {
            return o.toString();
        }
        //创建对象
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        //参数
        JSONObject bizContent = new JSONObject();
        bizContent.put( "out_trade_no", aliPay.getTime());
        bizContent.put( "total_amount", aliPay.getTotalAmount());
        bizContent.put( "subject", aliPay.getSubject());
        bizContent.put( "product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toString());
        //返回一个String支付页面
        String form = "";
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } //将订单号存入redis
        redisTemplate.opsForValue().set("orderNum_" + aliPay.getTime(),aliPay.getOutTradeNo());
        //将支付页面存入redis
        redisTemplate.opsForValue().set("pay_" + aliPay.getOutTradeNo(), form, 30, TimeUnit.MINUTES);

        return form;

    }

    @Override
    public String refund(AliPay aliPay) {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("trade_no", aliPay.getTradeNo());
        bizContent.put("refund_amount", aliPay.getTotalAmount());
        bizContent.put("out_request_no", System.currentTimeMillis() + "");
        request.setBizContent(bizContent.toString());
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        if (response.isSuccess()) {
            return "退款调用成功";
        }
        return "退款调用失败";
    }

    @Override
    public Map<String, String> query(AliPay aliPay) {
        AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        if (aliPay.getOutTradeNo() != null) {
            model.setOutTradeNo(aliPay.getOutTradeNo());
        }
        if (aliPay.getTradeNo() != null) {
            model.setTradeNo(aliPay.getTradeNo());
        }
        if (aliPay.getTime() != null) {
            model.setOutTradeNo(aliPay.getTime().toString());
        }
        alipayTradeQueryRequest.setBizModel(model);
        Map map = null;
        Map result = null;
        try {
            AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.execute(alipayTradeQueryRequest);
            String queryPaymentStr = alipayTradeQueryResponse.getBody();
            map = JSON.parseObject(queryPaymentStr, Map.class);

            result = JSON.parseObject(map.get("alipay_trade_query_response").toString(), Map.class);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String close(AliPay aliPay) {
        AlipayTradeCloseRequest alipayTradeCloseRequest = new AlipayTradeCloseRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", aliPay.getOutTradeNo());
        alipayTradeCloseRequest.setBizContent(bizContent.toString());
        AlipayTradeCloseResponse execute = null;
        try {
            execute = alipayClient.execute(alipayTradeCloseRequest);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        if (execute.isSuccess()) {
            return "关闭成功";
        }
        return "关闭失败";
    }
}


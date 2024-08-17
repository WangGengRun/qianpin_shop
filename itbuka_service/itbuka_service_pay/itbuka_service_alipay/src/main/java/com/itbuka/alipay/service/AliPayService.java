package com.itbuka.alipay.service;
import com.itbuka.alipay.domain.AliPay;

import java.util.Map;

public interface AliPayService {
    /**
     * H5支付
     *
     * @param aliPay
     * @return
     */
    String H5Pay(AliPay aliPay);
    /**
     * 退款

     * @param aliPay
     * @return
     */
    String refund(AliPay aliPay);
    /**
     * 查询订单

     * @param aliPay
     * @return
     */
    Map<String,String> query(AliPay aliPay);
    /**
     * 关闭订单
     * @param aliPay
     * @return
     */
    String close(AliPay aliPay);
}


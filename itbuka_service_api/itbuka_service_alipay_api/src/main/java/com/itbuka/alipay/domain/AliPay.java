package com.itbuka.alipay.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AliPay {
    //订单号
    private String outTradeNo;
    //交易金额
    private BigDecimal totalAmount;
    //标题
    private String subject;
    //支付宝订单号
    private String tradeNo;
    //订单创建时间
    private Long time;
}



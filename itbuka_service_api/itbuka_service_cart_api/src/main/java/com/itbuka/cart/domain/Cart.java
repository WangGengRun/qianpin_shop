package com.itbuka.cart.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Cart implements Serializable {
    //商品id
    private Long productId;
    //商品名称
    private String name;
    //单价
    private BigDecimal unitPrice;
    //商品数量
    private Integer num;
    //合计金额
    private BigDecimal money;
}


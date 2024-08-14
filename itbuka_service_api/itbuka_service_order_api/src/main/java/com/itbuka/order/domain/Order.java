package com.itbuka.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName itbuka_order
 */
@TableName(value ="itbuka_order")
@Data
public class Order implements Serializable {
    /**
     * 订单号
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 订单来源（0 移动端 1 pc端 2 小程序）
     */
    @TableField(value = "source")
    private Integer source;

    /**
     * 订单类型（0 普通订单 1 虚拟订单 2秒杀订单）
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 买家名称
     */
    @TableField(value = "buyer_name")
    private String buyerName;

    /**
     * 	订单金额
     */
    @TableField(value = "money")
    private BigDecimal money;

    /**
     * 订单状态（0 未付款 1 已付款 2 待发货 3 已发货 4 待核验 5待自提 6 已完成 7 已关闭）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 下单时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 删除标识（0 未删除 1已删除）
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 收货地址id
     */
    @TableField(value = "address_id")
    private Long addressId;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 数量
     */
    @TableField(value = "num")
    private Integer num;

    /**
     * 支付宝交易凭证号
     */
    @TableField(value = "trade_no")
    private String tradeNo;

    /**
     * 配送方式
     */
    @TableField(value = "shipping_method")
    private String shippingMethod;

    /**
     * 物流单号
     */
    @TableField(value = "tracking_number")
    private String trackingNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
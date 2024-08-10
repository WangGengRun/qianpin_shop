package com.itbuka.goods.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 商品详情表
 * @TableName goods_product_details
 */
@TableName(value ="goods_product_details")
@Data
public class ProductDetails implements Serializable {
    /**
     * 商品详情id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 规格id
     */
    @TableField(value = "spec_id")
    private String specId;

    /**
     * 编号
     */
    @TableField(value = "num")
    private Integer num;

    /**
     * 重量(kg)
     */
    @TableField(value = "weight")
    private BigDecimal weight;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 库存
     */
    @TableField(value = "inventory")
    private Integer inventory;

    /**
     * 图片
     */
    @TableField(value = "img")
    private String img;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 删除标识（0未删 1已删）
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
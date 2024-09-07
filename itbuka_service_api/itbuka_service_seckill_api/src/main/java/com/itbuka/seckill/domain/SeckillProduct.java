package com.itbuka.seckill.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 秒杀商品表
 * @TableName itbuka_seckill_product
 */
@TableName(value ="itbuka_seckill_product")
@Data
public class SeckillProduct implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 秒杀id
     */
    @TableField(value = "seckill_id")
    private Long seckillId;

    /**
     * 商品名称
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    private Long productId;

    /**
     * 商品价格
     */
    @TableField(value = "product_money")
    private Integer productMoney;

    /**
     * 库存
     */
    @TableField(value = "num")
    private Integer num;

    /**
     * 活动价格
     */
    @TableField(value = "seckill_money")
    private Integer seckillMoney;

    /**
     * 商家名称
     */
    @TableField(value = "merchant_name")
    private String merchantName;

    /**
     * 活动场次
     */
    @TableField(value = "activity_times")
    private String activityTimes;

    /**
     * 删除标识
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
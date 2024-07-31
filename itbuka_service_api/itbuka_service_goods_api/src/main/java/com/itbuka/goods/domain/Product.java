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
 * 商品表
 * @TableName goods_product
 */
@TableName(value ="goods_product")
@Data
public class Product implements Serializable {
    /**
     * 商品编号
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 商品名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 销售模式
     */
    @TableField(value = "sales_model")
    private String salesModel;

    /**
     * 商品类型
     */
    @TableField(value = "type")
    private String type;

    /**
     * 上下架状态(0上架1下架 )
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 审核状态(0未审核 1通过 2拒绝)
     */
    @TableField(value = "audit")
    private Integer audit;

    /**
     * 店铺名称
     */
    @TableField(value = "shop_name")
    private String shopName;

    /**
     * 商品分类id
     */
    @TableField(value = "classify_id")
    private Long classifyId;

    /**
     * 商品卖点
     */
    @TableField(value = "selling")
    private String selling;

    /**
     * 商品参数
     */
    @TableField(value = "parameter")
    private String parameter;

    /**
     * 计量单位
     */
    @TableField(value = "unit_measure")
    private String unitMeasure;

    /**
     * 商品图片
     */
    @TableField(value = "img")
    private String img;

    /**
     * 商品视频
     */
    @TableField(value = "video")
    private String video;

    /**
     * 商品描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 删除标识(0未删 1已删)
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

    /**
     * 品牌id
     */
    @TableField(value = "brand_id")
    private Long brandId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
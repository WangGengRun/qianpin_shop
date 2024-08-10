package com.itbuka.goods.domain;

import lombok.Data;


import java.io.Serializable;
import java.util.Map;


@Data
public class Detail implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 分类名称
     */
    private String category;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品卖点
     */
    private String selling;
    /**
     * 商品参数
     */
    private String parameter;
    /**
     * 计量单位
     */
    private String unitMeasure;
    /**
     * 销售模式
     */
    private String salesModel;
    /**
     * 商品价格
     */
    private Double price;
    /**
     * 商品图片
     */
    private String img;
    /**
     * 商品视频
     */
    private String video;
    /**
     * 商品详情（规格参数）
     */
    private Map spec;
    /**
     * 商品描述
     */
    private String description;

}

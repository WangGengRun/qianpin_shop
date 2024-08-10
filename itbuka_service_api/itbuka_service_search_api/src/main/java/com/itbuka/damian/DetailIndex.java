package com.itbuka.damian;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import java.util.Map;

@Document(indexName = "detailindex",type = "docs")
@Data
public class DetailIndex implements Serializable {
    /**
     * id
     */
    @TableId
    @Field(index = true, store = true,type = FieldType.Keyword)
    private String id;
    /**
     * 分类名称
     */
    @Field(index = true, store = true,type = FieldType.Text)
    private String category;
    /**
     * 商品名称
     */
    @Field(index = true, store = true,type = FieldType.Text,analyzer = "ik_smart")
    private String name;
    /**
     * 商品卖点
     */
    @Field(index = true, store = true,type = FieldType.Text)
    private String selling;
    /**
     * 商品参数
     */
    @Field(index = true, store = true,type = FieldType.Text)
    private String parameter;
    /**
     * 计量单位
     */
    @Field(index = true, store = true,type = FieldType.Text)
    private String unitMeasure;
    /**
     * 销售模式
     */
    @Field(index = true, store = true,type = FieldType.Text)
    private String salesModel;
    /**
     * 商品价格
     */
    @Field(index = true, store = true,type = FieldType.Double)
    private Double price;
    /**
     * 商品图片
     */
    @Field(index = true, store = true,type = FieldType.Keyword)
    private String img;
    /**
     * 商品视频
     */
    @Field(index = true, store = true,type = FieldType.Keyword)
    private String video;
    /**
     * 商品详情（规格参数）
     */
    private Map spec;
    /**
     * 商品描述
     */
    @Field(index = true, store = true,type = FieldType.Text)
    private String description;

}

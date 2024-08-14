package com.itbuka.goods.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName itbuka_address
 */
@TableName(value ="itbuka_address")
@Data
public class Address implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 收件人
     */
    @TableField(value = "addressee")
    private String addressee;

    /**
     * 收件地区
     */
    @TableField(value = "receiving_area")
    private String receivingArea;

    /**
     * 详细地址
     */
    @TableField(value = "full_address")
    private String fullAddress;

    /**
     * 手机号码

     */
    @TableField(value = "phone_num")
    private Integer phoneNum;

    /**
     * 默认地址（0否 1是）
     */
    @TableField(value = "default_address")
    private String defaultAddress;

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
     * 删除标识（0未删 1已删）
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
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
 * @TableName goods_vip
 */
@TableName(value ="goods_vip")
@Data
public class Vip implements Serializable {

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "vip_name")
    private String vipName;

    /**
     * 
     */
    @TableField(value = "vip_nickname")
    private String vipNickname;

    /**
     * 
     */
    @TableField(value = "vip_phone")
    private String vipPhone;

    /**
     * 
     */
    @TableField(value = "vip_time")
    private Date vipTime;

    /**
     * 
     */
    @TableField(value = "vip_num")
    private Integer vipNum;

    /**
     * 
     */
    @TableField(value = "vip_del")
    private String vipDel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
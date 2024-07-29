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
 * @TableName vip_appraise
 */
@TableName(value ="vip_appraise")
@Data
public class VipAppraise implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 
     */
    @TableField(value = "vip_name")
    private String vipName;

    /**
     * 0:好评
1：差评
     */
    @TableField(value = "appraise_kind")
    private Integer appraiseKind;

    /**
     * 物流评分
     */
    @TableField(value = "steam_score")
    private Integer steamScore;

    /**
     * 服务评分
     */
    @TableField(value = "service_score")
    private Integer serviceScore;

    /**
     * 描述评分
     */
    @TableField(value = "describe_score")
    private Integer describeScore;

    /**
     * 评价时间
     */
    @TableField(value = "appraise_time")
    private Date appraiseTime;

    /**
     * 0：启用
1：停用
     */
    @TableField(value = "page_status")
    private Integer pageStatus;

    /**
     * 0：未删除
1：已删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
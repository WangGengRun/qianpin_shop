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
 * @TableName vip_funds
 */
@TableName(value ="vip_funds")
@Data
public class VipFunds implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员名称
     */
    @TableField(value = "vip_name")
    private String vipName;

    /**
     * 变动金额
     */
    @TableField(value = "change_funds")
    private String changeFunds;

    /**
     * 变更时间
     */
    @TableField(value = "change_time")
    private Date changeTime;

    /**
     * 业务类型
     */
    @TableField(value = "job_type")
    private String jobType;

    /**
     * 详细
     */
    @TableField(value = "detail")
    private Integer detail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
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
 * @TableName vip_point_history
 */
@TableName(value ="vip_point_history")
@Data
public class VipPointHistory implements Serializable {
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
    @TableField(value = "operate_text")
    private String operateText;

    /**
     * 
     */
    @TableField(value = "before_point")
    private Integer beforePoint;

    /**
     * 
     */
    @TableField(value = "change_point")
    private Integer changePoint;

    /**
     * 
     */
    @TableField(value = "current_point")
    private Integer currentPoint;

    /**
     * 
     */
    @TableField(value = "operate_time")
    private Date operateTime;

    /**
     * 
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
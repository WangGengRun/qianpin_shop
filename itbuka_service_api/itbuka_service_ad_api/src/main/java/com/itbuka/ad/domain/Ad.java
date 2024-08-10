package com.itbuka.ad.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 广告表
 * @TableName ad
 */
@TableName(value ="ad")
@Data
public class Ad implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 广告名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 广告位置（0左侧 1轮播图 2右侧 3下方）
     */
    @TableField(value = "position")
    private Integer position;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 到期时间
     */
    @TableField(value = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 图片地址
     */
    @TableField(value = "image")
    private String image;

    /**
     * 访问链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 0未删1已删
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
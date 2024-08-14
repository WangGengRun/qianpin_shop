package com.itbuka.order.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName tb_task
 */
@TableName(value ="tb_task")
@Data
public class Task implements Serializable {
    /**
     * 任务id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 
     */
    @TableField(value = "delete_time")
    private Date deleteTime;

    /**
     * 任务类型
     */
    @TableField(value = "task_type")
    private String taskType;

    /**
     * 交换机名称
     */
    @TableField(value = "mq_exchange")
    private String mqExchange;

    /**
     * routingkey
     */
    @TableField(value = "mq_routingkey")
    private String mqRoutingkey;

    /**
     * 任务请求的内容
     */
    @TableField(value = "request_body")
    private String requestBody;

    /**
     * 任务状态
     */
    @TableField(value = "status")
    private String status;

    /**
     * 任务错误信息
     */
    @TableField(value = "errormsg")
    private String errormsg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
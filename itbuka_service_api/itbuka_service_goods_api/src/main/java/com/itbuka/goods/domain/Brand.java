package com.itbuka.goods.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName(value ="goods_brand")
public class Brand implements Serializable {
    /**
     * 品牌id
     */
    @TableId
    private Long id;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌图标
     */
    private String brandLogo;

    /**
     * 状态(0启用 1停用)
     */
    private Integer status;

    /**
     * 删除标识（0未删除 1已删除）
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    public Long getId() {
        return id;
    }

    /**
     * 品牌id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 品牌名称
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 品牌图标
     */
    public String getBrandLogo() {
        return brandLogo;
    }

    /**
     * 品牌图标
     */
    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    /**
     * 状态(0启用 1停用)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态(0启用 1停用)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 删除标识（0未删除 1已删除）
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 删除标识（0未删除 1已删除）
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

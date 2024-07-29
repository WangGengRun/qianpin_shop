package com.itbuka.goods.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName(value ="goods_classify")
public class Classify implements Serializable {
    /**
     * 分类ID
     */
    @TableId
    private Long id;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 分类图标
     */
    private String classifyLogo;

    /**
     * 排序值
     */
    private Integer seq;

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

    /**
     * 佣金
     */
    private String commission;

    /**
     * 上级ID
     */
    private Long isuperiorId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 分类ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 分类名称
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * 分类名称
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * 分类图标
     */
    public String getClassifyLogo() {
        return classifyLogo;
    }

    /**
     * 分类图标
     */
    public void setClassifyLogo(String classifyLogo) {
        this.classifyLogo = classifyLogo;
    }

    /**
     * 排序值
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * 排序值
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
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

    /**
     * 佣金
     */
    public String getCommission() {
        return commission;
    }

    /**
     * 佣金
     */
    public void setCommission(String commission) {
        this.commission = commission;
    }

    /**
     * 上级ID
     */
    public Long getIsuperiorId() {
        return isuperiorId;
    }

    /**
     * 上级ID
     */
    public void setIsuperiorId(Long isuperiorId) {
        this.isuperiorId = isuperiorId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Classify other = (Classify) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getClassifyName() == null ? other.getClassifyName() == null : this.getClassifyName().equals(other.getClassifyName()))
                && (this.getClassifyLogo() == null ? other.getClassifyLogo() == null : this.getClassifyLogo().equals(other.getClassifyLogo()))
                && (this.getSeq() == null ? other.getSeq() == null : this.getSeq().equals(other.getSeq()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getCommission() == null ? other.getCommission() == null : this.getCommission().equals(other.getCommission()))
                && (this.getIsuperiorId() == null ? other.getIsuperiorId() == null : this.getIsuperiorId().equals(other.getIsuperiorId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClassifyName() == null) ? 0 : getClassifyName().hashCode());
        result = prime * result + ((getClassifyLogo() == null) ? 0 : getClassifyLogo().hashCode());
        result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCommission() == null) ? 0 : getCommission().hashCode());
        result = prime * result + ((getIsuperiorId() == null) ? 0 : getIsuperiorId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", classifyName=").append(classifyName);
        sb.append(", classifyLogo=").append(classifyLogo);
        sb.append(", seq=").append(seq);
        sb.append(", status=").append(status);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", commission=").append(commission);
        sb.append(", isuperiorId=").append(isuperiorId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

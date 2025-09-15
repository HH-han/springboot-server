package com.example.travel.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;

/**
 * 安全知识提示实体类
 */
@Table(name = "travel_safetytips")
@Data
public class SafetyTips {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    @Size(min = 2, max = 100, message = "标题长度必须在2-100个字符之间")
    private String title;

    /**
     * 描述内容
     */
    @NotBlank(message = "描述内容不能为空")
    @Size(min = 10, max = 2000, message = "描述内容长度必须在10-2000个字符之间")
    private String description;

    /**
     * 图片URL
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 排序权重
     */
    @Min(value = 0, message = "排序权重不能小于0")
    @Max(value = 999, message = "排序权重不能大于999")
    private Integer sortWeight = 0;

    /**
     * 状态 (0-禁用, 1-启用)
     */
    @Min(value = 0, message = "状态值不合法")
    @Max(value = 1, message = "状态值不合法")
    private Integer status = 1;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 更新人ID
     */
    private Long updaterId;

    // 以下是分页参数(非数据库字段)
    @Transient
    private Integer pageNum = 1;

    @Transient
    private Integer pageSize = 10;

    @Transient
    private Integer offset;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    /**
     * 分类名称(非数据库字段)
     */
    @Transient
    private String categoryName;

    // 枚举定义
    public enum Status {
        DISABLED(0, "禁用"),
        ENABLED(1, "启用");

        private final int code;
        private final String desc;

        Status(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "标题不能为空") @Size(min = 2, max = 100, message = "标题长度必须在2-100个字符之间") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "标题不能为空") @Size(min = 2, max = 100, message = "标题长度必须在2-100个字符之间") String title) {
        this.title = title;
    }

    public @NotBlank(message = "描述内容不能为空") @Size(min = 10, max = 2000, message = "描述内容长度必须在10-2000个字符之间") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "描述内容不能为空") @Size(min = 10, max = 2000, message = "描述内容长度必须在10-2000个字符之间") String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public @Min(value = 0, message = "排序权重不能小于0") @Max(value = 999, message = "排序权重不能大于999") Integer getSortWeight() {
        return sortWeight;
    }

    public void setSortWeight(@Min(value = 0, message = "排序权重不能小于0") @Max(value = 999, message = "排序权重不能大于999") Integer sortWeight) {
        this.sortWeight = sortWeight;
    }

    public @Min(value = 0, message = "状态值不合法") @Max(value = 1, message = "状态值不合法") Integer getStatus() {
        return status;
    }

    public void setStatus(@Min(value = 0, message = "状态值不合法") @Max(value = 1, message = "状态值不合法") Integer status) {
        this.status = status;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
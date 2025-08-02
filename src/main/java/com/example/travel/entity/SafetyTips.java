package com.example.travel.entity;

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

}
package com.ming.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * @author liuming
 * @description
 * @date 2022/9/8
 */
@Data
public class BasePO implements Serializable {
    public final static String DEFAULT_USERNAME = "system";

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增ID
     */
    @TableId(type = IdType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @JsonFormat(pattern = Common.DATE_FORMAT, timezone = Common.TIMEZONE)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @JsonFormat(pattern = Common.DATE_FORMAT, timezone = Common.TIMEZONE)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createBy = DEFAULT_USERNAME;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updateBy = DEFAULT_USERNAME;

    /**
     * 逻辑删除标识
     * not null：删除
     * null：未删除
     */
    @TableLogic(delval = "now()", value = "null")
//    @TableField(select = false)
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private LocalDateTime deleteTime;


}

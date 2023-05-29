package com.ming.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author liuming
 * @description
 * @date 2023/3/22
 */
@Getter
@Setter
@TableName("sent_gen_table")
public class GenTable {
    private static final long serialVersionUID = 1L;

    private String tableName;

    private Boolean allowGen;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createBy;

    private String updateBy;
}

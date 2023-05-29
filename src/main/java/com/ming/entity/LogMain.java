package com.ming.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liuming
 * @description
 * @date 2022/9/8
 */
@Data
@TableName("t_sys_log_main")
public class LogMain {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String accountAffiliationCode;
    private String accountAffiliation;
    private LocalDateTime operationTime;
    private String operationKey;
    private String operationValue;
    private String operationLoginid;
    private String operationMessage;
    private String operationIp;
}

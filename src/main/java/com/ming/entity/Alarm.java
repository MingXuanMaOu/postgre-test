package com.ming.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liuming
 * @description
 * @date 2022/9/7
 */
@Data
@TableName("test2")
public class Alarm {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String alarmType;
    private LocalDateTime happenTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String deviceId;
}

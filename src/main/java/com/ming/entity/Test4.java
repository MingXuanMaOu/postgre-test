package com.ming.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liuming
 * @description
 * @date 2022/9/8
 */
@Data
@TableName("test4")
public class Test4 {

    private LocalDateTime logdate;
    private int peaktemp;
}

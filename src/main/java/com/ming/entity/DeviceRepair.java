package com.ming.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author liuming
 * @description
 * @date 2022/9/8
 */
@Data
@TableName("sent_device_repair")
public class DeviceRepair extends BasePO{

    private static final long serialVersionUID = 1L;

    /**
     * 提交人
     */
    private String submitBy;

    /**
     * 提交时间
     */
    private LocalDateTime submitTime;

    /**
     * 报修设备
     */
    private Long deviceId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备型号
     */
    private String deviceCode;

//  /**
//   *  设备类型
//   */
//      private Integer deviceType;

    /**
     * 设备描述
     */
    private String deviceDescription;

    /**
     * 报修桥涵
     */
    private Long bridgeId;

    /**
     * 桥涵名称
     */
    private String bridgeName;

    /**
     * 桥涵编号
     */
    private String bridgeCode;

    /**
     * 桥涵线路
     */
    private String bridgeRailway;

    /**
     * 桥涵地址
     */
    private String bridgeAddress;

    /**
     * 桥涵描述
     */
    private String bridgeDescription;

    /**
     * 报修信息
     */
    private String repairValue;

    /**
     * 0未查看，1查看
     */
    private Boolean checked;

    /**
     * 查看人
     */
    private String checkedBy;

    /**
     * 查看时间
     */
    private Date checkedTime;

    /**
     * 0未修理，1开始修理，2修理完成
     */
    private Integer repaired;

    /**
     * 修理人
     */
    private String repairBy;

    /**
     * 开始修理时间
     */
    private Date repairTime;

    /**
     * 修理完成时间
     */
    private Date repairedTime;

//      /**
//     * 修理进度
//     */
//      private Integer repairPlan;

    /**
     * 修理信息描述
     */
    private String repairDescription;

    /**
     * 组织ID
     */
    private Integer deptId;

    /**
     * 组织名称
     */
    private String deptName;

    /**
     * 组织编码
     */
    private String deptCode;

    private String description;
}

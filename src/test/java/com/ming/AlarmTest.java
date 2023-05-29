package com.ming;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ming.entity.Alarm;
import com.ming.entity.DeviceRepair;
import com.ming.entity.LogMain;
import com.ming.entity.Test4;
import com.ming.mapper.AlarmMapper;
import com.ming.mapper.DeviceRepairMapper;
import com.ming.mapper.LogMainMapper;
import com.ming.mapper.Test4Mapper;
import com.ming.service.impl.PostgreServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuming
 * @description
 * @date 2022/9/7
 */
@SpringBootTest
public class AlarmTest {

    @Autowired
    AlarmMapper mapper;
    @Autowired
    LogMainMapper logMainMapper;
    @Autowired
    Test4Mapper test4Mapper;
    @Autowired
    PostgreServiceImpl postgreService;

    @Autowired
    private DeviceRepairMapper deviceRepairMapper;

    @Autowired
    DataSource dataSource;
    String tableName = "sent_msg_mqtt";

    String[] tableNames = {"sent_alarm_event","sent_alarm_event_log","sent_alarm_info","sent_alarm_picture","sent_alarm_video","sent_alarm_video_task","sent_android_device","sent_android_manufacturer","sent_bridge","sent_bridge_image","sent_bridge_xgj","sent_camera_video","sent_device","sent_device_camera","sent_device_crash","sent_device_driver","sent_device_model","sent_device_move","sent_device_repair","sent_dict_code","sent_driver_point","sent_driver_point_rule","sent_gen_table","sent_log_device_crash","sent_log_device_move","sent_log_system","sent_msg_mqtt","sent_notice_bridge_setting","sent_notice_user_setting","sent_sys_dept"
            ,"sent_sys_dict","sent_sys_dict_detail","sent_sys_job","sent_sys_menu","sent_sys_notify","sent_sys_quartz_job","sent_sys_quartz_log","sent_sys_role","sent_sys_roles_depts","sent_sys_roles_menus","sent_sys_user","sent_sys_users_jobs","sent_sys_users_roles","sent_user_notice_setting","sent_user_wx"};

    @Test
    public void 序列号修改为最大(){
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            for (String tableName : tableNames) {

                try {
                    String sql = "select setval ('" + tableName + "_id_seq',(select max(id) from " + tableName + "))";
//                    String sql = "select setval ('" + tableName + "_id_seq',1)";


                    System.out.println(sql);
                    statement.execute(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void 序列号修改为最大1(){
        String newTable = "sent_msg_mqtt";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();


                try {
//                    String sql = "select setval ('" + tableName + "_id_seq',(select max(id) from " + tableName + "))";
                    String sql = "select setval ('" + newTable + "_id_seq',4029650)";


                    System.out.println(sql);
                    statement.execute(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void 建立序列(){
        try {
            String seq = "CREATE SEQUENCE \"public\"." + "\"" + tableName + "_id_seq\"" +
                    " INCREMENT 1\n" +
                    " MINVALUE 1\n" +
                    " MAXVALUE 99999999\n" +
                    " START 1\n" +
                    " CACHE 1;";
            String table = "ALTER TABLE \"public\"." + "\"" + tableName + "_id_seq\"" + " OWNER TO \"dataset\"";
            System.out.println(seq);
            System.out.println();
            System.out.println(table);

            Connection connection = dataSource.getConnection();
            connection.createStatement().execute(seq);
            connection.createStatement().execute(table);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void 将tinyint改为bool(){

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            String column = "app_bound";
            String alertVarchar = "alter table " + tableName + " alter column " + column + " type varchar using " + column + "::varchar";
            String updateTrue = "update " + tableName + " set " + column + "='true'" + " where " + column + "='1'";
            String updateFalse = "update " + tableName + " set " + column + "='false'" + " where " + column + "='0'";
            String alertBool = "alter table " + tableName + " alter column " + column + " type bool using " + column + "::bool";
            statement.execute(alertVarchar);
            statement.execute(updateTrue);
            statement.execute(updateFalse);
            statement.execute(alertBool);

            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Test
    public void 建立子表(){
        try {
            LocalDateTime localDateTime = LocalDateTime.of(2023, 3, 1, 0, 0);
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            for (int i = 0; i < 1; i++) {
                LocalDateTime time = localDateTime.plusMonths(i);
                LocalDateTime rangeTime = time.plusMonths(1);
                DateTimeFormatter table_df = DateTimeFormatter.ofPattern("_yyyy_MM");
                DateTimeFormatter time_df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String sql = "CREATE TABLE " + tableName + time.format(table_df) + " partition of " + tableName+ " for values from " + "('" + time.format(time_df) + "')" + " to ('" + rangeTime.format(time_df) + "')";
                System.out.println(sql);
                statement.execute(sql);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }





    @Test
    public void alarmTest(){

        LocalDateTime time = LocalDateTime.of(2020,1,1,0,0);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("_yyyy_MM");
        DateTimeFormatter dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(time.format(df));
        System.out.println(time.format(dd));
//        try {
//            Connection connection = dataSource.getConnection();
//            connection.createStatement().execute("create table sent_device_repair_2021_05 partition of sent_device_repair  for values from ('2021-05-01') to  ('2021-06-01')");
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        DeviceRepair deviceRepair = new DeviceRepair();
//        deviceRepair.setSubmitBy("system");
//        deviceRepair.setChecked(true);
//        deviceRepair.setRepaired(1);
//        deviceRepair.setCreateTime(LocalDateTime.now());
//        deviceRepairMapper.insert(deviceRepair);

//        List<DeviceRepair> deviceRepairs = deviceRepairMapper.selectList(Wrappers.emptyWrapper());
//        for (DeviceRepair deviceRepair : deviceRepairs) {
//            System.out.println(deviceRepair);
//        }

//        Test4 test4 = new Test4();
//        test4.setLogdate(LocalDateTime.now());
//        test4.setPeaktemp(2);
//
//        test4Mapper.insert(test4);
//        Alarm alarm = new Alarm();
//        alarm.setId(100000L);
//        alarm.setAlarmType("typ111e");
//        alarm.setCreateTime(LocalDateTime.now());
//        alarm.setHappenTime(LocalDateTime.now().plusMonths(1));
//        alarm.setDeviceId("device Id");
//        mapper.insert(alarm);

//        for (int i = 100000; i < 150000; i++) {
//            Alarm alarm = new Alarm();
//            alarm.setAlarmType("type " + i);
//            alarm.setCreateTime(LocalDateTime.now().plusMinutes(i));
//            alarm.setHappenTime(LocalDateTime.now().plusMinutes(i));
//            alarm.setDeviceId("device " + i);
//            mapper.insert(alarm);
//        }
//

//        for (int i = 0; i < 2880; i++) {
//            LogMain logMain = new LogMain();
//            logMain.setAccountAffiliationCode("2022028" + i);
//            logMain.setAccountAffiliation("测试学习" + i);
//            logMain.setOperationTime(LocalDateTime.now().plusHours(i));
//            logMain.setOperationKey(i + "");
//            logMain.setOperationValue("x" + i);
//            logMain.setOperationLoginid("zhsz_t" + i);
//            logMain.setOperationMessage("message" + i);
//            logMain.setOperationIp("127.0.0.1");
//
//            logMainMapper.insert(logMain);
//        }


    }

    @Value("${spring.datasource.driver-class-name}")
    private String deviceClassName;

    @Test
    public void 测试Value(){
        System.out.println(deviceClassName);
    }

    @Test
    public void 测试User(){
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void 创建序列号(){
        postgreService.createSerial();
    }

    @Test
    public void 创建划分时段(){
        postgreService.createPartitions();
    }

    @Test
    public void 将Tiny值转为Bool值(){
        List<String> list = new ArrayList<>();
        list.add("prev_exist");
        list.add("prev_download");
        list.add("download");

        postgreService.changeTinyToBool("rail_video_terminal",list);
    }

    @Test
    public void 添加序列(){
        postgreService.addSerial();
    }
}

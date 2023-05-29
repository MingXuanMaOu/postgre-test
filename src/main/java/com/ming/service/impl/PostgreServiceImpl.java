package com.ming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.entity.GenTable;
import com.ming.mapper.PostgreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author liuming
 * @description
 * @date 2023/3/22
 */
@Service
public class PostgreServiceImpl extends ServiceImpl<PostgreMapper, GenTable> {

    @Autowired
    DataSource dataSource;

    public void createSerial(){
        List<GenTable> genTables = getBaseMapper().selectList(Wrappers.emptyWrapper());
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (GenTable genTable : genTables) {
            String tableName = genTable.getTableName();
            try {
                String seq = "CREATE SEQUENCE \"public\"." + "\"" + tableName + "_id_seq\"" +
                        " INCREMENT 1\n" +
                        " MINVALUE 1\n" +
                        " MAXVALUE 9999999999\n" +
                        " START 1\n" +
                        " CACHE 1;";
                String table = "ALTER TABLE \"public\"." + "\"" + tableName + "_id_seq\"" + " OWNER TO \"dataset\"";
                System.out.println(seq);
                System.out.println();
                System.out.println(table);


                connection.createStatement().execute(seq);
                connection.createStatement().execute(table);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void createPartitions(){
        LambdaQueryWrapper<GenTable> wrapper = Wrappers.<GenTable>lambdaQuery()
                .eq(GenTable:: getAllowGen,true);
        List<GenTable> genTables = getBaseMapper().selectList(wrapper);

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (GenTable genTable : genTables) {
            String tableName = genTable.getTableName();
            try {
                LocalDateTime localDateTime = LocalDateTime.of(2023, 5, 1, 0, 0);
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
    }

    public void changeTinyToBool(String tableName,List<String> columns){


        Connection connection = null;
        try{
            connection = dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (String column : columns) {
            try {

                Statement statement = connection.createStatement();

                String alertVarchar = "alter table " + tableName + " alter column " + column + " type varchar using " + column + "::varchar";
                String updateTrue = "update " + tableName + " set " + column + "='true'" + " where " + column + "='1'";
                String updateFalse = "update " + tableName + " set " + column + "='false'" + " where " + column + "='0'";
                String alertBool = "alter table " + tableName + " alter column " + column + " type bool using " + column + "::bool";
                statement.execute(alertVarchar);
                statement.execute(updateTrue);
                statement.execute(updateFalse);
                statement.execute(alertBool);


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addSerial(){
        List<GenTable> genTables = getBaseMapper().selectList(Wrappers.emptyWrapper());

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (GenTable genTable : genTables) {
            try {
                String tableName = genTable.getTableName();
                String sql = "ALTER TABLE " + tableName + " ALTER COLUMN id SET DEFAULT nextval('" + tableName + "_id_seq'::regclass);";

                System.out.println(sql);
                Statement statement = connection.createStatement();
                statement.execute(sql);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

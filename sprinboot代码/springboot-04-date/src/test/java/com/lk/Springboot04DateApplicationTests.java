package com.lk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DateApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        //查看以下默认的数据源  class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());


        //获得数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //  xxxxTemplate : springboot 已经配置好的模板bean ，拿来即用
        connection.close();
    }

}

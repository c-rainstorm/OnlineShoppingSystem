package com.groupnine.oss.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库实用工具类
 * 
 * @author c-rainstorm
 *
 */
public class DBUtil {
    private static DruidDataSource dataSource = null;

    private static DBConfig dbConfig = new DBConfig();

    /**
     * 获取一个数据库连接
     * 
     * 如果未配置数据库连接池，则先配置连接池，再进行连接获取
     * 
     * @return 一个数据库连接
     */
    public static Connection getConnection() {
        if (dataSource == null) {
            dataSource = new DruidDataSource();

            dataSource.setDriverClassName(dbConfig.getDriver());
            dataSource.setUrl(getUrlForDatabase(dbConfig));
            dataSource.setUsername(dbConfig.getUsername());
            dataSource.setPassword(dbConfig.getPassword());
        }

        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            if (connection == null) {
                try {
                    dataSource.restart();

                    connection = dataSource.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private static String getUrlForDatabase(DBConfig databaseConfig) {
        StringBuilder builder = new StringBuilder(50);

        switch (databaseConfig.getDriver()) {
        case "com.mysql.jdbc.Driver":
            builder.append("jdbc:mysql://"
                    + dbConfig.getHost() + ":" + dbConfig.getPort()
                    + "/" + dbConfig.getName()
                    + "?&useSSL=false&useUnicode=yes&characterEncoding=UTF-8");
            break;

        default:
            break;
        }

        return builder.toString();
    }

    @Test
    public void testGetConnection() {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();

            System.out.println(conn.toString());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

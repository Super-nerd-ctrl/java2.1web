package com.yt.javaweb.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MyC3P0Util {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    private MyC3P0Util() {

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("获取连接异常！");
            e.printStackTrace();
        }

        return connection;
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("归还连接异常！");
            e.printStackTrace();
        }
    }
}

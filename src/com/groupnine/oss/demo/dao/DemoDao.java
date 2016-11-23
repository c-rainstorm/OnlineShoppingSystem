package com.groupnine.oss.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.groupnine.oss.util.DBUtil;

public class DemoDao {
    @Test
    public void getOrderId() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();

            preparedStatement = connection.prepareStatement("select * from `order`;");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getLong("order_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

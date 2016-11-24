package com.groupnine.oss.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.groupnine.oss.util.DBUtil;

public class DemoDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Test
    public void getOrderId() {

        try {
            connection = DBUtil.getConnection();

            preparedStatement = connection
                    .prepareStatement("select * from `order` where order_id = ?;");
            preparedStatement.setLong(1, 1);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("order_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
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

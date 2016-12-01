package com.groupnine.oss.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.groupnine.oss.admin.entity.Admin;
import com.groupnine.oss.admin.entity.Transaction;
import com.groupnine.oss.seller.entity.Shop;
import com.groupnine.oss.user.entity.User;
import com.groupnine.oss.util.DBUtil;

public class AdminDaoImpl implements AdminDao {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * close resultSet, preparedStatement, connection
     */
    private void closeAll() {
        try {

            if (resultSet != null)
                resultSet.close();

            if (preparedStatement != null)
                preparedStatement.close();

            if (connection != null)
                connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkLoginInfo(Admin admin) {
        boolean flag = false;
        String sql = "select admin_id from admin where admin_id = ? and password = md5(?);";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, admin.getAdminId());
            preparedStatement.setString(2, admin.getPassword());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return flag;
    }

    // 更新事务
    public boolean updatetransation(Transaction transaction) {
        boolean flag = false;

        String sql = "UPDATE transaction set admin_id=? , transaction_status = ? , complete_time=now(), annotation=? WHERE transaction_id = ?;";

        try {
            connection = DBUtil.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transaction.getAdminId());
            preparedStatement.setString(2, transaction.getTransactionStatus());
            preparedStatement.setString(3, transaction.getAnnotation());
            preparedStatement.setInt(4, transaction.getTransactionId());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return flag;
    }

    public Transaction getTransactionDetail(Transaction transaction) {
        String sql = "select * from transaction where transaction_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, transaction.getTransactionId());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                transaction.setTransactionId(resultSet.getInt("phone"));
                transaction.setUserId(resultSet.getInt("status"));
                transaction.setAdminId(resultSet.getInt("store_photo_addr"));
                transaction.setTransactionType(resultSet.getString("store_name"));
                transaction.setTransactionStatus(resultSet.getString("store_address"));
                transaction.setComment(resultSet.getString("category"));
                transaction.setCommitTime(resultSet.getTimestamp("description"));
                transaction.setCompleteTime(resultSet.getTimestamp("announcement"));
                transaction.setAnnotation(resultSet.getString("delivery_fee"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return transaction;

    }

    @Override
    public boolean DeleteUser(int uid) {
        boolean flag = false;

        String sql = "UPDATE user set is_valid = false WHERE user_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, uid);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;

    }

    @Override
    public boolean DeleteShop(String phone) {
        boolean flag = false;

        String sql = "UPDATE shop set is_valid = false  WHERE phone= ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, phone);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }

    @Override
    public ArrayList<Transaction> GetUnsolvedTransaction() {
        ArrayList<Transaction> todotransactions = new ArrayList<>();

        String sql = "SELECT * FROM transaction where transaction_status='未处理'   ORDER BY transaction_id";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();

                transaction
                        .setTransactionId(Integer.parseInt(resultSet.getString("transaction_id")));
                transaction.setComment(resultSet.getString("comment"));
                transaction.setCommitTime(Timestamp.valueOf(resultSet.getString("commit_time")));

                todotransactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return todotransactions;

    }

    @Override
    public ArrayList<Transaction> GetDoneTransaction() {
        ArrayList<Transaction> donetransactions = new ArrayList<>();

        String sql = "SELECT * FROM transaction where transaction_status='已拒绝'  OR transaction_status='已通过'   ORDER BY transaction_id";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();

                transaction.setComment(resultSet.getString("comment"));
                transaction.setCommitTime(Timestamp.valueOf(resultSet.getString("commit_time")));
                transaction
                        .setCompleteTime(Timestamp.valueOf(resultSet.getString("complete_time")));

                transaction
                        .setTransactionId(Integer.parseInt(resultSet.getString("transaction_id")));
                donetransactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return donetransactions;
    }

    @Override
    public ArrayList<Shop> GetShopInfo(String phone, String shop_name) {
        ArrayList<Shop> shops = new ArrayList<>();

        String sql = "SELECT * FROM shop WHERE phone= ? OR shop_name like ? ORDER BY shop_id";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);

            preparedStatement.setString(2, shop_name + "%");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Shop shop = new Shop();

                shop.setShopId(Integer.parseInt(resultSet.getString("shop_id")));
                shop.setShopName(resultSet.getString("shop_name"));
                shop.setPhone(resultSet.getString("phone"));
                shops.add(shop);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return shops;
    }

    @Override
    public ArrayList<Transaction> SearchTransaction(int transactionId, String comment) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM transaction WHERE transaction_id= ? OR comment like ? ORDER BY transaction_id";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transactionId);

            preparedStatement.setString(2, comment + "%");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();

                transaction.setComment(resultSet.getString("comment"));
                transaction.setCommitTime(Timestamp.valueOf(resultSet.getString("commit_time")));
                transaction
                        .setCompleteTime(Timestamp.valueOf(resultSet.getString("complete_time")));

                transaction
                        .setTransactionId(Integer.parseInt(resultSet.getString("transaction_id")));
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return transactions;
    }

    @Override
    public ArrayList<User> GetUserInfo(String phone, String username) {
        ArrayList<User> users = new ArrayList<>();

        String sql = "SELECT * FROM user WHERE phone= ? OR username like ? ORDER BY user_id";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phone);

            preparedStatement.setString(2, username + "%");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();

                user.setUserId(Integer.parseInt(resultSet.getString("user_id")));
                user.setUsername(resultSet.getString("username"));
                user.setPhone(resultSet.getString("phone"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return users;
    }

}

// 更新事务状态
/*
 * public void changetransaction_status(String transaction_id) { String sql =
 * "update transaction set transaction_status = (0x1^transaction_status) where transaction_id = ?;"
 * ; try { connection = DBUtil.getConnection(); preparedStatement =
 * connection.prepareStatement(sql); preparedStatement.setString(1,
 * transaction_id); preparedStatement.executeUpdate(); } catch (SQLException e)
 * { e.printStackTrace(); }finally { closeAll(); } } }
 */

package com.groupnine.oss.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.groupnine.oss.user.entity.GoodsItemInSC;
import com.groupnine.oss.user.entity.UserLoginInfo;
import com.groupnine.oss.user.entity.UserSessionInfo;
import com.groupnine.oss.util.DBUtil;

public class UserDaoMySQLImpl implements UserDao {

    public boolean matchUserPassword(UserLoginInfo info) {
        boolean loginSuccess = false;

        String loginMethod = "username";
        String identify = info.getUsername();
        if (info.getUsername() == null) {
            loginMethod = "phone";
            identify = info.getPhone();
        }

        String sql = "select * from user where " + loginMethod
                + " = ? and password = md5(?) and is_valid=true;";

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            sttmt = conn.prepareStatement(sql);

            sttmt.setString(1, identify);
            sttmt.setString(2, info.getPassword());

            ResultSet resultSet = sttmt.executeQuery();

            if (resultSet.next())
                loginSuccess = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return loginSuccess;
    }

    public UserSessionInfo getUserSessionInfo(UserLoginInfo info) {
        String userId = "";
        String shopHasOpend = "";
        // 因为 avatarAddr 为 null 时不能更改 session，所以初始值设为 null
        String avatarAddr = null;
        String shopId = "";
        String nickname = "";

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();

            String loginMethod = "username";
            String identify = info.getUsername();
            if (info.getUsername() == null) {
                loginMethod = "phone";
                identify = info.getPhone();
            }
            String queryUserAndShop = "select user_id, nickname, avatar, shop_id, apply_status " +
                    "from user join shop using (user_id) " +
                    "where user." + loginMethod +
                    " = ? and user.is_valid=true and shop.is_valid=true";
            sttmt = conn.prepareStatement(queryUserAndShop);
            sttmt.setString(1, identify);
            rs = sttmt.executeQuery();
            if (rs.next()) {
                userId = rs.getString(1);
                nickname = rs.getString(2);
                avatarAddr = rs.getString(3);

                shopId = rs.getString(1);
                String shopStatus = rs.getString(5);
                shopHasOpend = shopStatus.equals("已通过") ? "ture"
                        : (shopStatus.equals("待审核") ? "reviewing"
                                : "false");
                if (!shopHasOpend.equals("false")) {
                    shopId = rs.getString(4);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return new UserSessionInfo(userId, nickname, avatarAddr, shopHasOpend, shopId);
    }

    public boolean checkUsernameAvil(String username) {
        boolean avil = false;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();

            String queryUsername = "select username from user where username = ?;";
            sttmt = conn.prepareStatement(queryUsername);
            sttmt.setString(1, username);
            rs = sttmt.executeQuery();

            if (rs.next() == false)
                avil = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return avil;
    }

    public boolean checkPhoneAvil(String phone) {
        boolean avil = false;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();

            String queryPhone = "select username from user where phone = ?;";
            sttmt = conn.prepareStatement(queryPhone);
            sttmt.setString(1, phone);
            rs = sttmt.executeQuery();

            if (rs.next() == false)
                avil = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return avil;
    }

    public boolean addNewUser(UserLoginInfo userLoginInfo) {
        boolean avil = false;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();

            String insertUser = "insert into user(username, phone, nickname, password) values(?,?,?, md5(?));";
            sttmt = conn.prepareStatement(insertUser);
            sttmt.setString(1, userLoginInfo.getUsername());
            sttmt.setString(2, userLoginInfo.getPhone());
            // nickname 默认为 username
            sttmt.setString(3, userLoginInfo.getUsername());
            sttmt.setString(4, userLoginInfo.getPassword());

            int count = sttmt.executeUpdate();
            if (count != 0) {
                avil = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return avil;
    }

    public void addGoodsToShoppingCart(String userId, GoodsItemInSC goods) {
        System.out.println("yes, add");
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        System.out.println("add local shopping cart");
        try {
            conn = DBUtil.getConnection();

            String insertGoods = "insert into shopping_cart(user_id, attribute_id, goods_id, goods_num)"
                    + "values(?,?,?,?);";
            sttmt = conn.prepareStatement(insertGoods);
            sttmt.setInt(1, Integer.valueOf(userId));
            sttmt.setInt(2, Integer.valueOf(goods.getAttributeId()));
            sttmt.setInt(3, Integer.valueOf(goods.getGoodsId()));
            sttmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
    }

    // Privates

    private void close(Connection conn, PreparedStatement sttmt, ResultSet rs) {
        try {
            if (conn != null)
                conn.close();
            if (sttmt != null)
                sttmt.close();
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

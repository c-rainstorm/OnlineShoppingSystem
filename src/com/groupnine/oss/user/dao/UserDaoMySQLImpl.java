package com.groupnine.oss.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.groupnine.oss.pub.entity.MalformedException;
import com.groupnine.oss.user.entity.Category;
import com.groupnine.oss.user.entity.FavoriteShop;
import com.groupnine.oss.user.entity.FavortiteGoods;
import com.groupnine.oss.user.entity.GoodsAttrString;
import com.groupnine.oss.user.entity.GoodsBrief;
import com.groupnine.oss.user.entity.GoodsDetail;
import com.groupnine.oss.user.entity.GoodsInOrder;
import com.groupnine.oss.user.entity.GoodsInOrderInfo;
import com.groupnine.oss.user.entity.GoodsItemInSC;
import com.groupnine.oss.user.entity.GoodsItemInfo;
import com.groupnine.oss.user.entity.OrderBrief;
import com.groupnine.oss.user.entity.OrderDetail;
import com.groupnine.oss.user.entity.Receiver;
import com.groupnine.oss.user.entity.SCOrder;
import com.groupnine.oss.user.entity.SearchOption;
import com.groupnine.oss.user.entity.ShopBrief;
import com.groupnine.oss.user.entity.ShoppingCart;
import com.groupnine.oss.user.entity.UserFullInfo;
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
        String shopHasOpend = "false";
        String avatarAddr = "";
        String shopId = "none";
        String nickname = "";

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();

            /* 1. 查 user 表取得 user_id, nickname */

            String loginMethod = "username";
            String identify = info.getUsername();
            if (info.getUsername() == null) {
                loginMethod = "phone";
                identify = info.getPhone();
            }
            String queryUser = "select user_id, nickname, avatar from user where " + loginMethod
                    + " = ? and is_valid=true;";
            sttmt = conn.prepareStatement(queryUser);
            sttmt.setString(1, identify);
            rs = sttmt.executeQuery();
            rs.next();
            userId = rs.getString(1);
            nickname = rs.getString(2);
            avatarAddr = rs.getString(3);
            sttmt.close();
            rs.close();

            /* 2. 查询店铺表 */

            String queryShop = "select shop_id, apply_status from shop where user_id = ? and is_valid=true";
            sttmt = conn.prepareStatement(queryShop);
            sttmt.setInt(1, Integer.valueOf(userId));
            rs = sttmt.executeQuery();
            if (rs.next()) {
                shopId = rs.getString(1);
                String shopStatus = rs.getString(2);
                shopHasOpend = shopStatus.equals("已通过") ? "ture"
                        : (shopStatus.equals("待审核") ? "reviewing"
                                : "false");
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
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        boolean exist = false;
        try {
            conn = DBUtil.getConnection();

            /* 1. 先查询商品是否已在购物车中 */

            String querySC = "select * from shopping_cart "
                    + "where user_id = ? and goods_id = ? and attribute_id = ?"
                    + "  and is_valid=true;";
            sttmt = conn.prepareStatement(querySC);
            sttmt.setInt(1, Integer.valueOf(userId));
            sttmt.setInt(2, Integer.valueOf(goods.getGoodsId()));
            sttmt.setInt(3, Integer.valueOf(goods.getAttributeId()));
            rs = sttmt.executeQuery();
            if (rs.next()) {
                exist = true;
            }
            sttmt.close();
            rs.close();

            /* 2. 添加到购物车 */

            String insertGoods = exist
                    ? "update shopping_cart set goods_num=goods_num + ? "
                            + "where user_id=? and attribute_id=? and goods_id=?"
                    : "insert into shopping_cart(goods_num, user_id, attribute_id, goods_id) "
                            + "values(?,?,?,?);";
            sttmt = conn.prepareStatement(insertGoods);
            sttmt.setInt(1, Integer.valueOf(goods.getGoodsNum()));
            sttmt.setInt(2, Integer.valueOf(userId));
            sttmt.setInt(3, Integer.valueOf(goods.getAttributeId()));
            sttmt.setInt(4, Integer.valueOf(goods.getGoodsId()));
            sttmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
    }

    public ArrayList<ShoppingCart> getShoppingCart(String userId) {
        ArrayList<ShoppingCart> scs = new ArrayList<>();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        /* 两步走：第一步查购物车相关，第二步查商品属性 */

        try {
            /*
             * 1. 查 shopping_cart 表 取得用户购物车内商品 id, goods_id, attribute_id
             * goods_num 等
             */

            conn = DBUtil.getConnection();

            String querySC = "select shopping_cart.id, goods_id, goods_name, goods_describe, image_addr, "
                    + "  attribute_id, goods_num, shop.shop_id,shop.shop_name "
                    + "from ((shopping_cart join goods using(goods_id)) "
                    + "  join ("
                    + "    select goods_id, max(image_addr) as image_addr"
                    + "    from goods_image"
                    + "    where is_valid=true "
                    + "    group by goods_id ) as T"
                    + "  using (goods_id))"
                    + "  join shop using(shop_id) "
                    + "where shopping_cart.user_id = ? "
                    + "  and shopping_cart.is_valid=true and goods.is_valid=true and shop.is_valid=true;";
            sttmt = conn.prepareStatement(querySC);
            sttmt.setInt(1, Integer.valueOf(userId));
            rs = sttmt.executeQuery();

            while (rs.next()) {
                GoodsItemInSC g = new GoodsItemInSC();
                GoodsItemInfo gi = new GoodsItemInfo();
                try {
                    g.setId(rs.getString(1));
                    g.setAttributeId(rs.getString(6));
                    g.setGoodsNum(rs.getString(7));
                    gi.setGoodsId(rs.getString(2));
                    gi.setGoodsName(rs.getString(3));
                    gi.setGoodsDescribe(rs.getString(4));
                    gi.setImageAddr(rs.getString(5));
                    g.setGoodsInfo(gi);
                } catch (MalformedException e) {
                    // Omit this exception
                }

                // 判别商品所属店铺是否已在购物车 scs 中

                boolean shopHasAdded = false;
                int index = -1;
                String shopId = rs.getString(8);
                String shopName = rs.getString(9);
                for (int i = 0; i < scs.size(); i++) {
                    ShoppingCart sc = scs.get(i);
                    if (sc.getShopId().equals(shopId)) {
                        shopHasAdded = true;
                        index = i;
                        break;
                    }
                }

                // 根据商品所属店铺是否已在购物车中采取不同行为

                if (shopHasAdded) {
                    scs.get(index).addToThisSC(g);
                } else {
                    ShoppingCart newSC = new ShoppingCart(shopId, shopName);
                    newSC.setShopId(shopId);
                    newSC.setShopName(shopName);
                    newSC.addToThisSC(g);
                    scs.add(newSC);
                }
            } // while (rs.next())

            sttmt.close();
            rs.close();

            /* 2. 查 goods_attribute 表得到商品详细信息 */

            if (scs.size() != 0) {
                for (ShoppingCart sc : scs) {
                    for (GoodsItemInSC gg : sc.getGoodsInThisShop()) {

                        GoodsItemInfo gi = gg.getGoodsInfo();

                        String queryAttr = "select attribute_id, attribute_value, price, inventory, "
                                + "  discount_deadline, discount_rate "
                                + "from goods_attribute join goods using(goods_id) "
                                + "where goods_id = ? "
                                + "  and goods_attribute.is_valid=true and goods.is_valid;";
                        sttmt = conn.prepareStatement(queryAttr);
                        sttmt.setInt(1, Integer.valueOf(gi.getGoodsId()));
                        rs = sttmt.executeQuery();

                        while (rs.next()) {
                            boolean discounting = rs.getDate(5)
                                    .after(new Date(System.currentTimeMillis()));
                            double discountRate = rs.getDouble(6);

                            GoodsAttrString attr = new GoodsAttrString();
                            attr.setAttributeId(rs.getString(1));
                            attr.setAttributeValue(rs.getString(2));
                            attr.setPrice(discounting
                                    ? Double.toString(rs.getDouble(3) * discountRate)
                                    : Double.toString(rs.getDouble(3)));
                            attr.setInventory(rs.getString(4));
                            gi.addGoodsAttr(attr);
                        }

                        sttmt.close();
                        rs.close();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return scs;
    }

    public void deleteFromShoppingCart(String uid, String rid) {
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();

            String deleteGoods = "update shopping_cart set is_valid=false "
                    + "where user_id=? and id=? and is_valid=true;";
            sttmt = conn.prepareStatement(deleteGoods);
            sttmt.setInt(1, Integer.valueOf(uid));
            sttmt.setInt(2, Integer.valueOf(rid));
            sttmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
    }

    public boolean updateGoodsNumInShoppingCart(String uid, String rid, String goodNum) {
        boolean updated = false;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();

            String updateNum = "update shopping_cart set goods_num=? where user_id=? and id=? and is_valid=true;";
            sttmt = conn.prepareStatement(updateNum);
            sttmt.setInt(1, Integer.valueOf(goodNum));
            sttmt.setInt(2, Integer.valueOf(uid));
            sttmt.setInt(3, Integer.valueOf(rid));
            int rowAffected = sttmt.executeUpdate();
            updated = (rowAffected != 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return updated;
    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> cs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();

            String queryCategory = "select level_one, level_two from category where is_valid=true;";
            sttmt = conn.prepareStatement(queryCategory);
            rs = sttmt.executeQuery();

            while (rs.next()) {
                boolean hasAdded = false;
                int index = -1;
                for (int i = 0; i < cs.size(); i++) {
                    Category c = cs.get(i);
                    if (c.getName().equals(rs.getString(1))) {
                        hasAdded = true;
                        index = i;
                        break;
                    }
                }
                if (hasAdded) {
                    cs.get(index).addToSubLevel(rs.getString(2));
                } else {
                    Category newC = new Category();
                    newC.setName(rs.getString(1));
                    newC.addToSubLevel(rs.getString(2));
                    cs.add((newC));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return cs;
    }

    public ArrayList<GoodsBrief> getGoodsBriefByKeyword(SearchOption option) {
        ArrayList<GoodsBrief> gs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            /* 1. 获得除 attribute_id（技术原因无法得到） 以外所有的商品信息 */

            String queryGoods = "select goods.goods_id, goods_name, goods_describe, image_addr, sales, price "
                    +
                    "from ( goods " +
                    "  join ( select goods_id, max(image_addr) as image_addr " +
                    "    from goods_image" +
                    "    where is_valid=true" +
                    "    group by goods_id ) as T1" +
                    "  using (goods_id)) " +
                    "  join ( select goods_id, min(price) as price " +
                    "    from goods_attribute" +
                    "    where is_valid=true " +
                    "    group by goods_id ) as T2 " +
                    "  using (goods_id) " +
                    "where goods.is_valid=true and (goods_name like ? or goods_describe like ? )" +
                    "order by " +
                    (option.sortByPrice.equals("true")
                            ? (option.priceUp.equals("true")
                                    ? "price ASC "
                                    : "price DESC ")
                            : "sales DESC")
                    +
                    " limit ?, ?;";
            sttmt = conn.prepareStatement(queryGoods);
            sttmt.setString(1, "%" + option.keyword + "%");
            sttmt.setString(2, "%" + option.keyword + "%");
            long offset = (Long.valueOf(option.pageNum) - 1) *
                    Long.valueOf(option.nPerPage);
            sttmt.setLong(3, offset);
            sttmt.setInt(4, Integer.valueOf(option.nPerPage));
            rs = sttmt.executeQuery();

            while (rs.next()) {
                GoodsBrief g = new GoodsBrief();
                g.setGoodsId(rs.getString(1))
                        .setGoodsName(rs.getString(2))
                        .setGoodsDescribe(rs.getString(3))
                        .setImageAddr(rs.getString(4))
                        .setSales(rs.getString(5))
                        .setPrice(rs.getString(6));
                gs.add(g);
            }
            sttmt.close();
            rs.close();

            /* 2 匹配 goods_attribute 表得到商品 attribute_id */

            // 尽可能与第一步获得商品的顺序一致，而添加的 sort by
            String queryAttr = "select goods_id, attribute_id, price " +
                    "from goods_attribute where is_valid=true " +
                    (option.sortByPrice.equals("true")
                            ? (option.priceUp.equals("true")
                                    ? "order by price ASC "
                                    : "order by price DESC ")
                            : "");
            sttmt = conn.prepareStatement(queryAttr);
            rs = sttmt.executeQuery();

            // 逐个匹配
            while (rs.next()) {
                String gid = rs.getString(1);
                String aid = rs.getString(2);
                String price = rs.getString(3);

                for (int i = 0; i < gs.size(); i++) {
                    if (gs.get(i).getGoodsId().equals(gid) &&
                            gs.get(i).getPrice().equals(price)) {
                        gs.get(i).setAttributeId(aid);
                        break;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return gs;
    }

    public ArrayList<GoodsBrief> getGoodsBriefByCategory(SearchOption option) {
        ArrayList<GoodsBrief> gs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryGoods = "select goods.goods_id, goods_name, goods_describe, image_addr, sales, price "
                    +
                    "from (( goods " +
                    "  join ( select goods_id, max(image_addr) as image_addr" +
                    "    from goods_image" +
                    "    where is_valid=true" +
                    "    group by goods_id ) as T1" +
                    "  using(goods_id)) " +
                    "  join ( select goods_id, min(price) as price " +
                    "    from goods_attribute" +
                    "    where is_valid=true " +
                    "    group by goods_id ) as T2 " +
                    "  using (goods_id) ) " +
                    "  join category " +
                    "  using (category_id) " +
                    "where goods.is_valid=true and category.is_valid=true"
                    +
                    "  and level_one ='" + option.levelOne + "' " +
                    (option.levelTwo != null
                            ? "and level_two= '" + option.levelTwo + "' "
                            : "")
                    +
                    "order by " +
                    (option.sortByPrice.equals("true")
                            ? (option.priceUp.equals("true")
                                    ? "price ASC "
                                    : "price DESC ")
                            : "sales DESC ")
                    +
                    "limit ?, ?;";
            sttmt = conn.prepareStatement(queryGoods);
            long offset = (Long.valueOf(option.pageNum) - 1) *
                    Long.valueOf(option.nPerPage);
            sttmt.setLong(1, offset);
            sttmt.setInt(2, Integer.valueOf(option.nPerPage));

            rs = sttmt.executeQuery();

            while (rs.next()) {
                GoodsBrief g = new GoodsBrief();
                g.setGoodsId(rs.getString(1))
                        .setGoodsName(rs.getString(2))
                        .setGoodsDescribe(rs.getString(3))
                        .setImageAddr(rs.getString(4))
                        .setSales(rs.getString(5))
                        .setPrice(rs.getString(6));
                gs.add(g);
            }

            sttmt.close();
            rs.close();

            /* 2 匹配 goods_attribute 表得到商品 attribute_id */

            // 尽可能与第一步获得商品的顺序一致，而添加的 sort by
            String queryAttr = "select goods_id, attribute_id, price " +
                    "from goods_attribute where is_valid=true " +
                    (option.sortByPrice.equals("true")
                            ? (option.priceUp.equals("true")
                                    ? "order by price ASC "
                                    : "order by price DESC")
                            : "");
            sttmt = conn.prepareStatement(queryAttr);
            rs = sttmt.executeQuery();

            // 逐个匹配
            while (rs.next()) {
                String gid = rs.getString(1);
                String aid = rs.getString(2);
                String price = rs.getString(3);

                for (int i = 0; i < gs.size(); i++) {
                    if (gs.get(i).getGoodsId().equals(gid) &&
                            gs.get(i).getPrice().equals(price)) {
                        gs.get(i).setAttributeId(aid);
                        break;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return gs;
    }

    public ArrayList<GoodsBrief> getGoodsImageByL1(String levelOne, String imageNum) {
        ArrayList<GoodsBrief> gs = new ArrayList<>();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryGoods = "select goods.goods_id, image_addr " +
                    "from ( goods join category using(category_id) )" +
                    "  join ( ( select goods_id, max(image_addr) as image_addr" +
                    "       from goods_image" +
                    "       where is_valid=true" +
                    "       group by goods_id ) as T )" +
                    "  using  (goods_id) " +
                    "where goods.is_valid=true and level_one=? " +
                    "limit ?;";
            sttmt = conn.prepareStatement(queryGoods);
            sttmt.setString(1, levelOne);
            sttmt.setInt(2, Integer.valueOf(imageNum));
            rs = sttmt.executeQuery();

            while (rs.next()) {
                GoodsBrief g = new GoodsBrief();
                g.setGoodsId(rs.getString(1))
                        .setImageAddr(rs.getString(2));
                gs.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return gs;
    }

    public String getGoodsNumInSC(String uid) {
        String sum = "0";

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String querySC = "select sum(goods_num) from shopping_cart "
                    + "where user_id = ?"
                    + "  and is_valid=true;";
            sttmt = conn.prepareStatement(querySC);
            sttmt.setString(1, uid);
            rs = sttmt.executeQuery();

            if (rs.next()) {
                String n = rs.getString(1);
                if (n != null) {
                    sum = n;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return sum;
    }

    public GoodsDetail getGoodsDetail(String gid) {
        GoodsDetail detail = new GoodsDetail();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            // 1. 查询除 goods_image 以外的所有属性

            String queryGoods = "select goods_id, goods_name, goods_describe, discount_deadline, discount_rate, "
                    + "  attribute_id, attribute_value, price, inventory, level_one, level_two "
                    + "from ((goods join goods_attribute using(goods_id))"
                    + "  join category"
                    + "  using(category_id)) "
                    + "where goods.is_valid=true and goods_attribute.is_valid=true "
                    + "  and category.is_valid=true"
                    + "  and goods_id = ?;";
            sttmt = conn.prepareStatement(queryGoods);
            sttmt.setInt(1, Integer.valueOf(gid));
            rs = sttmt.executeQuery();

            if (rs.next()) {
                detail.setGoodsId(rs.getString(1));
                detail.setGoodsName(rs.getString(2));
                detail.setGoodsDescribe(rs.getString(3));
                detail.setDiscountDeadline(rs.getString(4));
                detail.setDiscountRate(rs.getString(5));
                GoodsAttrString attr = new GoodsAttrString();
                attr.setAttributeId(rs.getString(6));
                attr.setAttributeValue(rs.getString(7));
                attr.setPrice(rs.getString(8));
                attr.setInventory(rs.getString(9));
                detail.addAttr(attr);
                detail.setLevelOne(rs.getString(10));
                detail.setLevelTwo(rs.getString(11));
            }
            while (rs.next()) {
                GoodsAttrString a = new GoodsAttrString();
                a.setAttributeId(rs.getString(6));
                a.setAttributeValue(rs.getString(7));
                a.setPrice(rs.getString(8));
                a.setInventory(rs.getString(9));
                detail.addAttr(a);
            }

            sttmt.close();
            rs.close();

            // 2. 查询 image_addr

            String queryImg = "select image_addr from goods_image where is_valid=true "
                    + "  and goods_id = ?;";
            sttmt = conn.prepareStatement(queryImg);
            sttmt.setInt(1, Integer.valueOf(gid));
            rs = sttmt.executeQuery();

            while (rs.next()) {
                detail.addImage(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return detail;
    }

    public ShopBrief getShopBriefInfoByGoodsId(String gid) {
        ShopBrief gi = new ShopBrief();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryShop = "select shop_id, shop_name "
                    + "from goods join shop using(shop_id) "
                    + "where goods_id = ?;";
            sttmt = conn.prepareStatement(queryShop);
            sttmt.setString(1, gid);
            rs = sttmt.executeQuery();

            if (rs.next()) {
                gi.setShopId(rs.getString(1));
                gi.setShopName(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return gi;
    }

    public UserFullInfo getUserInfo(String uid) {
        UserFullInfo info = new UserFullInfo();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryUser = "select username, nickname, phone, sex, birthday "
                    + "from user "
                    + "where user_id = ? and is_valid=true;";
            sttmt = conn.prepareStatement(queryUser);
            sttmt.setString(1, uid);
            rs = sttmt.executeQuery();

            if (rs.next()) {
                info.setUsername(rs.getString(1));
                info.setNickname(rs.getString(2));
                info.setPhone(rs.getString(3));
                info.setSex(rs.getString(4));
                info.setBirthday(rs.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return info;
    }

    public void updateUserInfo(String uid, String type, String newValue) {
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String updateInfo = "update user "
                    + "set " + type + " = ? "
                    + "where user_id = ? and is_valid=true;";
            sttmt = conn.prepareStatement(updateInfo);
            sttmt.setString(1, newValue);
            sttmt.setInt(2, Integer.valueOf(uid));
            sttmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
    }

    public ArrayList<Receiver> getReceivers(String uid) {
        ArrayList<Receiver> receivers = new ArrayList<>();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryR = "select receiver_id, name, address, phone, used_times "
                    + "from receiver "
                    + "where user_id = ? and is_valid=true "
                    + "order by used_times desc;";
            sttmt = conn.prepareStatement(queryR);
            sttmt.setString(1, uid);
            rs = sttmt.executeQuery();
            while (rs.next()) {
                Receiver r = new Receiver();
                r.setReceiverId(rs.getString(1));
                r.setName(rs.getString(2));
                r.setAddress(rs.getString(3));
                r.setPhone(rs.getString(4));
                r.setUsedTimes(rs.getString(5));
                receivers.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return receivers;
    }

    public boolean addReceiver(String uid, Receiver r) {
        boolean added = false;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String add = "insert into receiver(user_id, name, address, phone) "
                    + "values(?, ?, ?, ?) ;";
            sttmt = conn.prepareStatement(add);
            sttmt.setString(1, uid);
            sttmt.setString(2, r.getName());
            sttmt.setString(3, r.getAddress());
            sttmt.setString(4, r.getPhone());
            int affected = sttmt.executeUpdate();
            if (affected != 0) {
                added = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return added;
    }

    public boolean modifyReceiver(Receiver r) {
        boolean changed = false;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String add = "update receiver "
                    + "set name = ?, address = ?, phone = ? "
                    + "where receiver_id=?";
            sttmt = conn.prepareStatement(add);
            sttmt.setString(1, r.getName());
            sttmt.setString(2, r.getAddress());
            sttmt.setString(3, r.getPhone());
            sttmt.setString(4, r.getReceiverId());
            int affected = sttmt.executeUpdate();
            if (affected != 0) {
                changed = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return changed;
    }

    public boolean deleteReceiver(String rid) {
        boolean deleted = false;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String add = "update receiver "
                    + "set is_valid=false "
                    + "where receiver_id=?";
            sttmt = conn.prepareStatement(add);
            sttmt.setString(1, rid);
            int affected = sttmt.executeUpdate();
            if (affected != 0) {
                deleted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return deleted;
    }

    public OrderDetail getOrderById(String oid) {
        OrderDetail d = new OrderDetail();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryDetail = "select goods_order.order_id, tracking_number, order_status, user.username, "
                    + "  receiver.name, receiver.address, receiver.phone, "
                    + "  goods.goods_id, goods_name, goods_describe, image_addr, "
                    + "  attribute_value, goods_num, actual_price, "
                    + "  pay_method, order_time, complete_time, annotation, total "
                    + "from ( goods_order join receiver using(receiver_id, user_id) )"
                    + "  join user using(user_id)"
                    + "  join goods_in_order using(order_id)"
                    + "  join goods using(goods_id)"
                    + "  join ("
                    + "    select goods_id, max(image_addr) as image_addr"
                    + "    from goods_image"
                    + "    where is_valid=true"
                    + "    group by goods_id ) as T"
                    + "  using(goods_id)"
                    + "  join goods_attribute using(attribute_id)"
                    + "where goods_order.is_valid=true"
                    + "    and receiver.is_valid=true"
                    + "    and user.is_valid=true"
                    + "    and goods.is_valid=true"
                    + "    and goods_attribute.is_valid=true"
                    + "    and goods_order.order_id=?; ";
            sttmt = conn.prepareStatement(queryDetail);
            sttmt.setString(1, oid);
            rs = sttmt.executeQuery();
            if (rs.next()) {
                d.orderId = rs.getString(1);
                d.trackingNumber = rs.getString(2);
                d.orderStatus = rs.getString(3);
                d.username = rs.getString(4);
                d.receiver.name = rs.getString(5);
                d.receiver.address = rs.getString(6);
                d.receiver.phone = rs.getString(7);
                d.goodsInOrder.add(new GoodsInOrder(new GoodsInOrderInfo(
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
                d.payMethod = rs.getString(15);
                d.orderTime = rs.getString(16);
                d.completeTime = rs.getString(17);
                d.annotation = rs.getString(18);
                d.total = rs.getString(19);
            }
            while (rs.next()) {
                d.goodsInOrder.add(new GoodsInOrder(new GoodsInOrderInfo(
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11)),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
        return d;
    }

    public ArrayList<FavortiteGoods> getGoodsFavorite(String uid, String nPerPage, String pageNum) {
        ArrayList<FavortiteGoods> goods = new ArrayList<FavortiteGoods>();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryFG = "select favorite_goods.goods_id, goods_name, goods_describe, "
                    + "  image_addr, sales, price "
                    + "from favorite_goods join goods using(goods_id) "
                    + "  join ("
                    + "    select goods_id, max(image_addr) as image_addr "
                    + "    from goods_image "
                    + "    where is_valid=true "
                    + "    group by goods_id "
                    + "  ) as T1 using(goods_id) "
                    + "  join ("
                    + "    select goods_id, avg(price) as price"
                    + "    from goods_attribute"
                    + "    where is_valid = true"
                    + "    group by goods_id"
                    + "  ) as T2 using(goods_id) "
                    + "where favorite_goods.is_valid = true"
                    + "  and goods.is_valid = true"
                    + "  and user_id = ? "
                    + "order by id desc "
                    + "limit ?, ?;";
            sttmt = conn.prepareStatement(queryFG);
            sttmt.setString(1, uid);
            int offset = (Integer.valueOf(pageNum) - 1) * Integer.valueOf(nPerPage);
            sttmt.setInt(2, offset);
            sttmt.setInt(3, Integer.valueOf(nPerPage));
            rs = sttmt.executeQuery();
            while (rs.next()) {
                FavortiteGoods g = new FavortiteGoods();
                g.goodsId = rs.getString(1);
                g.goodsName = rs.getString(2);
                g.goodsDescribe = rs.getString(3);
                g.imageAddr = rs.getString(4);
                g.sales = rs.getString(5);
                g.price = rs.getString(6);
                goods.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return goods;
    }

    public ArrayList<FavoriteShop> getShopFavorite(String uid, String nPerPage, String pageNum) {
        ArrayList<FavoriteShop> shops = new ArrayList<FavoriteShop>();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryFS = "select shop_id, shop_name "
                    + "from favorite_shops join shop using(shop_id) "
                    + "where favorite_shops.is_valid=true"
                    + "  and shop.is_valid=true"
                    + "  and favorite_shops.user_id = ? "
                    + "order by id desc "
                    + "limit ?, ?;";
            sttmt = conn.prepareStatement(queryFS);
            sttmt.setString(1, uid);
            int offset = (Integer.valueOf(pageNum) - 1) * Integer.valueOf(nPerPage);
            sttmt.setInt(2, offset);
            sttmt.setInt(3, Integer.valueOf(nPerPage));
            rs = sttmt.executeQuery();
            while (rs.next()) {
                FavoriteShop shop = new FavoriteShop();
                shop.shopId = rs.getString(1);
                shop.shopName = rs.getString(2);
                shops.add(shop);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return shops;
    }

    public void deleteGoodsFromFavorite(String uid, String gid) {
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String deleteG = "update favorite_goods set is_valid = false "
                    + "where user_id = ? and goods_id = ?"
                    + "  and is_valid=true;";
            sttmt = conn.prepareStatement(deleteG);
            sttmt.setString(1, uid);
            sttmt.setString(2, gid);
            sttmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
    }

    public void deleteShopFromFavorite(String uid, String sid) {
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String deleteG = "update favorite_shops set is_valid = false "
                    + "where user_id = ? and shop_id = ?"
                    + "  and is_valid=true;";
            sttmt = conn.prepareStatement(deleteG);
            sttmt.setString(1, uid);
            sttmt.setString(2, sid);
            sttmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
    }

    public void addGoodsToFavorite(String uid, String gid) {
        boolean exist = false;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryFG = "select * from favorite_goods "
                    + "where is_valid=true"
                    + "  and user_id = ?"
                    + "  and goods_id = ?;";
            sttmt = conn.prepareStatement(queryFG);
            sttmt.setString(1, uid);
            sttmt.setString(2, gid);
            rs = sttmt.executeQuery();
            if (rs.next())
                exist = true;
            conn.close();
            sttmt.close();
            rs.close();

            if (!exist) {
                conn = DBUtil.getConnection();

                String addFG = "insert into favorite_goods(user_id, goods_id) "
                        + "values(?, ?);";
                sttmt = conn.prepareStatement(addFG);
                sttmt.setString(1, uid);
                sttmt.setString(2, gid);
                sttmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }
    }

    public List<OrderBrief> getOrderByStatus(String uid, String orderStatus, String nPerPage,
            String pageNum) {

        ArrayList<OrderBrief> orders = new ArrayList<OrderBrief>();

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String queryOrder = "select order_id, shop_name, order_status "
                    + "from goods_order join shop using(shop_id) "
                    + "where goods_order.is_valid=true "
                    + "  and shop.is_valid=true "
                    + "  and goods_order.user_id = ? "
                    + "  and order_status like '" + orderStatus + "' "
                    + "limit ?, ?;";
            sttmt = conn.prepareStatement(queryOrder);
            sttmt.setString(1, uid);
            int offset = (Integer.valueOf(pageNum) - 1) * Integer.valueOf(nPerPage);
            sttmt.setInt(2, offset);
            sttmt.setInt(3, Integer.valueOf(nPerPage));

            rs = sttmt.executeQuery();

            while (rs.next()) {
                orders.add(new OrderBrief(rs.getString(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return orders;
    }

    public boolean updateOrderStatus(String oid, String newStat) {
        boolean success = false;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            String editOrder = "update goods_order "
                    + "set order_status = ? "
                    + "where order_id = ? "
                    + "  and is_valid=true;";
            sttmt = conn.prepareStatement(editOrder);
            sttmt.setString(1, newStat);
            sttmt.setString(2, oid);

            success = sttmt.executeUpdate() > 0 ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return success;
    }

    public String genOrder(String uid, SCOrder o, String rid, String payMethod) {
        String newOrderId = null;

        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            /* 1. 生成订单 */

            String genOrder = "insert into goods_order(user_id, shop_id, order_status, "
                    + "  pay_method, order_time, annotation, total, receiver_id) "
                    + "values( ?, ?, \"待付款\", "
                    + "  ?, now() , ?, 0, ? );";
            sttmt = conn.prepareStatement(genOrder);
            sttmt.setString(1, uid);
            sttmt.setString(2, o.shopId);
            sttmt.setString(3, payMethod);
            sttmt.setString(4, o.annotation);
            sttmt.setString(5, rid);
            sttmt.executeUpdate();

            sttmt.close();

            /* 2. 查询刚才生成的订单 id */

            String queryOrder = "select max(order_id) "
                    + "from goods_order "
                    + "where user_id = ?"
                    + "  and is_valid=true;";
            sttmt = conn.prepareStatement(queryOrder);
            sttmt.setString(1, uid);
            rs = sttmt.executeQuery();
            rs.next();
            newOrderId = rs.getString(1);

            sttmt.close();
            rs.close();

            /* 3. 转移 shopping_cart 到 goods_in_order */

            for (String recordId : o.id) {
                String addGoodsToOrder = "insert into goods_in_order(order_id,  attribute_id, "
                        + "  goods_id, goods_num, cost, actual_price) "
                        + "values "
                        + "("
                        + newOrderId
                        + ", (select attribute_id from shopping_cart "
                        + "     where id = \"" + recordId + "\")"
                        + ", (select goods_id from shopping_cart "
                        + "     where id = \"" + recordId + "\")"
                        + ", (select goods_num from shopping_cart "
                        + "     where id =\"" + recordId + "\")"
                        + ", (select cost*goods_num from shopping_cart join goods_attribute using(attribute_id) "
                        + "     where id = \"" + recordId + "\")"
                        + ", (select price*goods_num from shopping_cart join goods_attribute using(attribute_id) "
                        + "     where id = \"" + recordId + "\")"
                        + ");";
                sttmt = conn.prepareStatement(addGoodsToOrder);
                sttmt.executeUpdate();
                sttmt.close();

                String deleteSCItem = "update shopping_cart "
                        + "set is_valid=false "
                        + "where id = \"" + recordId + "\" ;";
                sttmt = conn.prepareStatement(deleteSCItem);
                sttmt.executeUpdate();
                sttmt.close();
            }

            /* 4 .计算商品总价，填入 goods_order */

            String calTotal = "update goods_order "
                    + "set total = "
                    + "  (select sum(actual_price) "
                    + "   from goods_in_order"
                    + "   where order_id = \"" + newOrderId + "\") "
                    + "where order_id = \"" + newOrderId + "\""
                    + ";";
            sttmt = conn.prepareStatement(calTotal);
            sttmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, sttmt, rs);
        }

        return newOrderId;
    }

    public void updateAvatar(String uid, String newAddr) {
        Connection conn = null;
        PreparedStatement sttmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            // TODO
            String updateAvatar = "update user "
                    + "set avatar = ? "
                    + "where user_id = ?;";
            sttmt = conn.prepareStatement(updateAvatar);
            sttmt.setString(1, newAddr);
            sttmt.setString(2, uid);
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

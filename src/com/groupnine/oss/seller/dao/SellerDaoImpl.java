package com.groupnine.oss.seller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import com.groupnine.oss.pub.entity.GoodsInOrder;
import com.groupnine.oss.pub.entity.Order;
import com.groupnine.oss.seller.entity.Goods;
import com.groupnine.oss.seller.entity.GoodsAttr;
import com.groupnine.oss.seller.entity.GoodsImage;
import com.groupnine.oss.seller.entity.Shop;
import com.groupnine.oss.seller.entity.StatisticsData;
import com.groupnine.oss.user.entity.Receiver;
import com.groupnine.oss.util.DBUtil;;

public class SellerDaoImpl implements SellerDao {
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

    public Shop getShopById(int userId) {
        String sql = "select * from shop where user_id = ?;";
        Shop shop = new Shop();
        shop.setUserId(userId);

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            shop.setPhone(resultSet.getString("phone"));
            shop.setShopId(resultSet.getInt("shop_id"));
            shop.setShopName(resultSet.getString("shop_name"));
            shop.setAddress(resultSet.getString("address"));
            shop.setShopDescribe(resultSet.getString("shop_describe"));
            shop.setAnnouncement(resultSet.getString("announcement"));
            shop.setEvaluateSum(resultSet.getLong("evaluate_sum"));
            shop.setEvaluateNumber(resultSet.getLong("evaluate_number"));
            shop.setApplyStatus(resultSet.getString("apply_status"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return shop;
    }// getShopByID over

    public boolean updateShopInfo(Shop shop) {
        boolean flag = false;

        String sql = "UPDATE shop set shop_name = ?, address = ?, phone = ?," +
                "shop_describe = ?, announcement = ? WHERE shop_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, shop.getShopName());
            preparedStatement.setString(2, shop.getAddress());
            preparedStatement.setString(3, shop.getPhone());
            preparedStatement.setString(4, shop.getShopDescribe());
            preparedStatement.setString(5, shop.getAnnouncement());
            preparedStatement.setInt(6, shop.getShopId());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return flag;
    }// updateShopInfo over

    public boolean updateGoodsInfo(Goods goods) {
        boolean flag = false;
        //
        // String sql = "UPDATE goods set goods_name = ?, goods_describe = ?,
        // discount_deadline = ?, "
        // + "discount_rate = ? WHERE goods_id = ?;";
        String sql = "UPDATE goods set goods_name = ?, goods_describe = ?, "
                + "discount_rate = ? WHERE goods_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, goods.getGoodsName());
            preparedStatement.setString(2, goods.getGoodsDescribe());
            // preparedStatement.setString(3, goods.getDiscountDeadline());
            preparedStatement.setDouble(3, goods.getDiscountRate());
            preparedStatement.setInt(4, goods.getGoodsId());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    } // updateGoodsInfo over

    public boolean sendGoods(Order order) {
        boolean flag = false;

        String sql = "UPDATE goods_order set order_status = ?, tracking_number = ?" +
                "WHERE order_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, "待收货");
            preparedStatement.setString(2, order.getTrackingNumber());
            preparedStatement.setLong(3, order.getOrderId());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }// sendGoodsStatus over

    public int addNewGoods(Goods goods) {
        int categoryId = 0;
        String sql = "select category_id from category where level_one = ? and level_two =?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, goods.getFirstCategory());
            preparedStatement.setString(2, goods.getSecondCategory());

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            categoryId = resultSet.getInt("category_id");

            // System.out.println(categoryId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        int autoIncKeyFromApi = -1;
        sql = "INSERT INTO goods(category_id, shop_id, goods_name, goods_describe) "
                + "VALUES (?, ?, ?, ?);";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, goods.getShopId());
            preparedStatement.setString(3, goods.getGoodsName());
            preparedStatement.setString(4, goods.getGoodsDescribe());

            int count = preparedStatement.executeUpdate();
            if (count == 0)
                return -1;

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);// Retrieves the value of the
                                                 // designated column in the
                                                 // current row of this
                                                 // ResultSet object as a long
                                                 // in the Java programming
                                                 // language.
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return autoIncKeyFromApi;
    }// addNewGoods over

    public boolean addGoodsImage(String imageDir, int goodsId) {
        boolean flag = false;

        String sql = "INSERT INTO goods_image(image_addr, goods_id) VALUES (?, ?);";// 待测试

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, imageDir);
            preparedStatement.setInt(2, goodsId);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }// addGoodsImage over

    public boolean deleteGoods(int goodsId) {
        boolean flag = false;

        String sql = "UPDATE goods set is_valid = 0 WHERE goods_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, goodsId);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;

    }// deleteGoods over

    public boolean addGoodsAttr(GoodsAttr attr) {
        boolean flag = false;

        String sql = "INSERT INTO goods_attribute(attribute_value, goods_id, cost, price, inventory) "
                + "VALUES (?, ?, ?, ?, ?);";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, attr.getAttributeValue());
            preparedStatement.setInt(2, attr.getGoodsId());
            preparedStatement.setDouble(3, attr.getCost());
            preparedStatement.setDouble(4, attr.getPrice());
            preparedStatement.setInt(5, attr.getInventory());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }// addGoodsAttr over

    public boolean updateGoodsAttr(GoodsAttr attr) {
        boolean flag = false;

        String sql = "UPDATE goods_attribute set attribute_value = ?, cost = ?, price = ?, "
                + "inventory = ? WHERE attribute_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, attr.getAttributeValue());
            preparedStatement.setDouble(2, attr.getCost());
            preparedStatement.setDouble(3, attr.getPrice());
            preparedStatement.setInt(4, attr.getInventory());
            preparedStatement.setInt(5, attr.getAttributeId());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }// updateGoodsAttr over

    public boolean deleteGoodsAttr(int attributeId) {
        boolean flag = false;

        String sql = "UPDATE goods_attribute set is_valid = false WHERE attribute_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, attributeId);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }// deleteGoodsAttr over

    public boolean deleteGoodsImage(int imageId) {
        boolean flag = false;

        String sql = "UPDATE goods_image set is_valid = false WHERE image_id = ?;";// 待测试

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, imageId);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }// deleteGoodsImage over

    public Goods getGoodsById(int goodsId) {
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);

        try {
            String sql = "select * from goods,category where goods_id = ? "
                    + "and goods.category_id=category.category_id and goods.is_valid = true;";

            connection = DBUtil.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsId);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            // System.out.println(resultSet.getString("goods_name"));

            goods.setGoodsName(resultSet.getString("goods_name"));
            goods.setCategoryId(resultSet.getInt("category_id"));
            goods.setFirstCategory(resultSet.getString("level_one"));
            goods.setSecondCategory(resultSet.getString("level_two"));
            goods.setShopId(resultSet.getInt("shop_id"));
            goods.setSales(resultSet.getInt("sales"));
            goods.setGoodsDescribe(resultSet.getString("goods_describe"));
            goods.setDiscountDeadline(resultSet.getString("discount_deadline"));
            goods.setDiscountRate(resultSet.getDouble("discount_rate"));

            // System.out.println(goods);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return goods;
    }// getShopByID over

    public ArrayList<GoodsImage> getImages(int goodsId) {
        ArrayList<GoodsImage> imageList = new ArrayList<GoodsImage>();

        try {
            String sql = "select * from goods_image where goods_id = ?;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GoodsImage gImage = new GoodsImage();
                gImage.setImageId(resultSet.getInt("image_id"));
                gImage.setGoodsId(resultSet.getInt("goods_id"));
                gImage.setImageAddr(resultSet.getString("image_addr"));

                imageList.add(gImage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return imageList;
    } // getImages over

    public ArrayList<String> getImagesUrl(int goodsId) {
        ArrayList<String> urlList = new ArrayList<String>();

        try {
            String sql = "select * from goods_image where goods_id = ?"
                    + "order by image_id DESC "
                    + "limit 0,6;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String url = new String();
                url = resultSet.getString("image_addr");

                urlList.add(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return urlList;
    } // getImagesUrl over

    public ArrayList<GoodsAttr> getAttrs(int goodsId) {
        ArrayList<GoodsAttr> attrList = new ArrayList<GoodsAttr>();

        try {
            String sql = "select * from goods_attribute where goods_id = ? and is_valid = 1;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GoodsAttr gAttr = new GoodsAttr();
                gAttr.setAttributeId(resultSet.getInt("attribute_id"));
                gAttr.setGoodsId(resultSet.getInt("goods_id"));
                gAttr.setAttributeValue(resultSet.getString("attribute_value"));
                gAttr.setCost(resultSet.getDouble("cost"));
                gAttr.setPrice(resultSet.getDouble("price"));
                gAttr.setInventory(resultSet.getInt("inventory"));

                attrList.add(gAttr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return attrList;
    } // getAttr over

    public ArrayList<StatisticsData> getSingleSales(int days, int goodsId) {
        ArrayList<StatisticsData> sList = new ArrayList<StatisticsData>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.DAY_OF_YEAR, -(days)); // 取得当前时间前的时间点

        java.util.Date dt = calendar.getTime();
        java.sql.Date date = new java.sql.Date(dt.getTime());

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // String str = sdf.format(calendar.getTime());

        try {
            String sql = "select DATE_FORMAT(order_time,'%Y%m%d') date, sum(goods_num) sum "
                    + "from goods_order, goods_in_order "
                    + "where goods_in_order.goods_id = ? "
                    + "and goods_order.order_id = goods_in_order.order_id "// 连接查询
                    + "and order_time > ? "
                    + "group by order_time;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, goodsId);
            preparedStatement.setString(2, date.toString());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StatisticsData sd = new StatisticsData();

                sd.setDate(resultSet.getString("date"));
                sd.setValue(resultSet.getInt("sum"));

                sList.add(sd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return sList;
    } // getSingleSales over

    public boolean registerShop(Shop shop) {
        boolean flag = false;

        String sql = "INSERT INTO shop(shop_name, address, phone, shop_describe, user_id) "
                + "VALUES (?, ?, ?, ?, ?);";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, shop.getShopName());
            preparedStatement.setString(2, shop.getAddress());
            preparedStatement.setString(3, shop.getPhone());
            preparedStatement.setString(4, shop.getShopDescribe());
            preparedStatement.setInt(5, shop.getUserId());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return flag;
    }// registerShop over

    public ArrayList<Order> getHistoryOrder(int shopId, int page) {
        ArrayList<Order> order = new ArrayList<Order>();

        try {
            String sql = "select * from goods_order where shop_id = ? "
                    + "and order_status='待收货' "
                    + "or order_status='待评价' "
                    + "or order_status='已完成' "
                    + "group by order_time DESC "
                    + "limit ?,10;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, shopId);
            preparedStatement.setInt(2, (page - 1) * 10);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order od = new Order();

                od.setOrderId(resultSet.getLong("order_id"));
                od.setShopId(resultSet.getInt("shop_id"));
                od.setUserId(resultSet.getInt("user_id"));
                od.setOrderStatus(resultSet.getString("order_status"));
                od.setTrackingNumber(resultSet.getString("tracking_number"));
                od.setPayMethod(resultSet.getString("pay_method"));
                od.setCompleteTime(resultSet.getTimestamp("complete_time"));
                od.setOrderTime(resultSet.getTimestamp("order_time"));
                od.setAnnotation(resultSet.getString("annotation"));
                od.setTotal(resultSet.getInt("total"));

                // int receiverId = resultSet.getInt("receiver_id");
                // Receiver receiver = new Receiver();
                // String sql1 = "select * from receiver where receiver_id =
                // ?;";
                // PreparedStatement ps = connection.prepareStatement(sql1);
                // ps.setInt(1, receiverId);
                // ResultSet rs = ps.executeQuery();
                // if (rs.next()) {
                // receiver.setUserId(rs.getInt("user_id"));
                // receiver.setReceiverId(rs.getInt("receiver_id"));
                // receiver.setAddress(rs.getString("address"));
                // receiver.setName(rs.getString("name"));
                // receiver.setPhone(rs.getString("phone"));
                // receiver.setUsedTimes(rs.getInt("used_times"));
                // }
                // od.setReceiver(receiver);// 获得收货人

                // od.setGoodsInOrder(getGoodsInOrder(resultSet.getLong("order_id")));

                order.add(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return order;
    } // getHistoryOrder over

    public ArrayList<Receiver> getReceiver(int userId) {
        ArrayList<Receiver> re = new ArrayList<Receiver>();
        Receiver receiver = new Receiver();
        try {
            String sql = "select * from receiver where user_id = ?;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                receiver.setUserId(resultSet.getInt("user_id"));
                receiver.setReceiverId(resultSet.getInt("receiver_id"));
                receiver.setAddress(resultSet.getString("address"));
                receiver.setName(resultSet.getString("name"));
                receiver.setPhone(resultSet.getString("phone"));
                receiver.setUsedTimes(resultSet.getInt("used_times"));
                re.add(receiver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return re;
    } // getReceiver over

    public ArrayList<GoodsInOrder> getGoodsInOrder(Long orderId) {
        ArrayList<GoodsInOrder> GIO = new ArrayList<GoodsInOrder>();
        GoodsInOrder gio = new GoodsInOrder();

        try {
            String sql = "select * from goods_in_order where order_id = ?;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, orderId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                gio.setOrderId(resultSet.getInt("order_id"));
                gio.setAttributeId(resultSet.getInt("attribute_id"));
                gio.setComment(resultSet.getString("comment"));
                gio.setGoodsNum(resultSet.getInt("goods_num"));

                {
                    Goods goods = new Goods();
                    int goodsId = resultSet.getInt("goods_id");
                    goods.setGoodsId(goodsId);
                    String sql1 = "select * from goods,category where goods_id = ? "
                            + "and goods.category_id=category.category_id and goods.is_valid = true;";
                    PreparedStatement ps1 = connection.prepareStatement(sql1);
                    ps1.setInt(1, goodsId);
                    ResultSet rs1 = ps1.executeQuery();
                    rs1.next();
                    goods.setGoodsName(resultSet.getString("goods_name"));
                    goods.setCategoryId(resultSet.getInt("category_id"));
                    goods.setFirstCategory(resultSet.getString("level_one"));
                    goods.setSecondCategory(resultSet.getString("level_two"));
                    goods.setShopId(resultSet.getInt("shop_id"));
                    goods.setSales(resultSet.getInt("sales"));
                    goods.setGoodsDescribe(resultSet.getString("goods_describe"));
                    goods.setDiscountDeadline(resultSet.getString("discount_deadline"));
                    goods.setDiscountRate(resultSet.getDouble("discount_rate"));
                    gio.setGoods(goods);
                }

                int attributeId = resultSet.getInt("attribute_id");
                String sql2 = "select * from goods_attribute where attribute_id = ?;";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                ps2.setInt(1, attributeId);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    gio.setAttributeValue(rs2.getString("attribute_value"));
                    gio.setCost(rs2.getDouble("cost"));
                    gio.setActualPrice(rs2.getDouble("price"));
                }

                GIO.add(gio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return GIO;
    } // getGoodsInOrder over

    public ArrayList<Order> getUnfinishedOrder(int shopId, int page) {
        ArrayList<Order> orders = new ArrayList<Order>();

        try {
            String sql = "select * from goods_order where shop_id = ? "
                    + "and order_status='待发货' "
                    + "group by order_time DESC "
                    + "limit ?,10;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, shopId);
            preparedStatement.setInt(2, (page - 1) * 10);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order od = new Order();

                od.setOrderId(resultSet.getLong("order_id"));
                od.setShopId(resultSet.getInt("shop_id"));
                od.setUserId(resultSet.getInt("user_id"));
                od.setOrderTime(resultSet.getTimestamp("order_time"));
                od.setAnnotation(resultSet.getString("annotation"));
                od.setTotal(resultSet.getInt("total"));

                // int receiverId = resultSet.getInt("receiver_id");
                // Receiver receiver = new Receiver();
                // String sql1 = "select * from receiver where receiver_id =
                // ?;";
                // PreparedStatement ps = connection.prepareStatement(sql1);
                // ps.setInt(1, receiverId);
                // ResultSet rs = ps.executeQuery();
                // if (rs.next()) {
                // receiver.setUserId(rs.getInt("user_id"));
                // receiver.setReceiverId(rs.getInt("receiver_id"));
                // receiver.setAddress(rs.getString("address"));
                // receiver.setName(rs.getString("name"));
                // receiver.setPhone(rs.getString("phone"));
                // }
                // od.setReceiver(receiver);// 获得收货人

                // od.setGoodsInOrder(getGoodsInOrder(resultSet.getLong("order_id")));

                orders.add(od);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return orders;
    } // getUnfinishedOrder over

    public ArrayList<StatisticsData> getTotalSales(int days, int shopId) {

        ArrayList<StatisticsData> sList = new ArrayList<StatisticsData>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add(Calendar.DAY_OF_YEAR, -(days)); // 取得当前时间前的时间点

        java.util.Date dt = calendar.getTime();
        java.sql.Date date = new java.sql.Date(dt.getTime());

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // String str = sdf.format(calendar.getTime());

        try {
            String sql = "select DATE_FORMAT(order_time,'%Y%m%d') date, sum(goods_num) sum "
                    + "from goods_order, goods_in_order "
                    + "where goods_order.shop_id = ? "
                    + "and goods_order.order_id = goods_in_order.order_id "// 连接查询
                    + "and order_time > ? "
                    + "group by DATE_FORMAT(order_time,'%Y%m%d');";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, shopId);
            preparedStatement.setString(2, date.toString());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StatisticsData sd = new StatisticsData();

                sd.setDate(resultSet.getString("date"));
                sd.setValue(resultSet.getInt("sum"));

                sList.add(sd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return sList;
    } // getTotalSales over

    public Order getOrderDetail(int orderId) {
        Order order = new Order();

        try {
            String sql = "select * from goods_order where order_id = ?;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            order.setOrderId(resultSet.getLong("order_id"));
            order.setShopId(resultSet.getInt("shop_id"));
            order.setUserId(resultSet.getInt("user_id"));
            order.setOrderStatus(resultSet.getString("order_status"));
            order.setTrackingNumber(resultSet.getString("tracking_number"));
            order.setPayMethod(resultSet.getString("pay_method"));
            order.setCompleteTime(resultSet.getTimestamp("complete_time"));
            order.setOrderTime(resultSet.getTimestamp("order_time"));
            order.setAnnotation(resultSet.getString("annotation"));
            order.setTotal(resultSet.getInt("total"));

            int receiverId = resultSet.getInt("receiver_id");
            Receiver receiver = new Receiver();
            String sql1 = "select * from receiver where receiver_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setInt(1, receiverId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                receiver.setUserId(rs.getInt("user_id"));
                receiver.setReceiverId(rs.getInt("receiver_id"));
                receiver.setAddress(rs.getString("address"));
                receiver.setName(rs.getString("name"));
                receiver.setPhone(rs.getString("phone"));
                // receiver.setUsedTimes(rs.getInt("used_times"));
            }
            order.setReceiver(receiver);// 获得收货人

            order.setGoodsInOrder(getGoodsInOrder(resultSet.getLong("order_id")));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return order;
    } // getOrderDetail over

    public ArrayList<Goods> getGoodsBriefs(int shopId, int page) {
        ArrayList<Goods> goodsArray = new ArrayList<Goods>();

        try {
            String sql = "select * from goods where shop_id = ? and goods.is_valid = 1 "
                    + "order by sales DESC "
                    + "limit ?,10;";

            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, shopId);
            preparedStatement.setInt(2, (page - 1) * 10);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Goods goods = new Goods();
                goods.setGoodsId(resultSet.getInt("goods_id"));
                goods.setGoodsName(resultSet.getString("goods_name"));
                goods.setSales(resultSet.getInt("sales"));

                goodsArray.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return goodsArray;
    }

    @Override
    public boolean deleteGoodsAttrs(int goodsId) {
        boolean flag = false;

        String sql = "UPDATE goods_attribute set is_valid = false WHERE goods_id = ?;";

        try {
            connection = DBUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, goodsId);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                flag = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return flag;
    }// deleteGoodsAttrs over

}

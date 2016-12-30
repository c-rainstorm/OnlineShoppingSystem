package com.groupnine.oss.user.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;

import com.groupnine.oss.user.dao.UserDao;
import com.groupnine.oss.user.dao.UserDaoMySQLImpl;
import com.groupnine.oss.user.entity.Category;
import com.groupnine.oss.user.entity.FavoriteShop;
import com.groupnine.oss.user.entity.FavortiteGoods;
import com.groupnine.oss.user.entity.GoodsBrief;
import com.groupnine.oss.user.entity.GoodsDetail;
import com.groupnine.oss.user.entity.GoodsItemInSC;
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

public class UserService {
    private static UserDao dao = new UserDaoMySQLImpl();

    public static boolean userLogin(UserLoginInfo userLoginInfo) {
        return dao.matchUserPassword(userLoginInfo);
    }

    public static UserSessionInfo getUserSession(UserLoginInfo loginInfo) {
        return dao.getUserSessionInfo(loginInfo);
    }

    public static boolean checkUsernameAvail(String username) {
        return dao.checkUsernameAvil(username);
    }

    public static boolean checkPhoneAvil(String phone) {
        return dao.checkPhoneAvil(phone);
    }

    public static boolean addNewUser(UserLoginInfo userLoginInfo) {
        return dao.addNewUser(userLoginInfo);
    }

    public static void addLocalShoppingCart(String userId, GoodsItemInSC[] local) {
        for (GoodsItemInSC goods : local) {
            addGoodsToShoppingCat(userId, goods);
        }
    }

    public static void addGoodsToShoppingCat(String userId, GoodsItemInSC goods) {
        dao.addGoodsToShoppingCart(userId, goods);
    }

    public static ArrayList<ShoppingCart> getShoppingCart(String uid) {
        return dao.getShoppingCart(uid);
    }

    public static void deleteFromShoppingCart(String rid, String recordId) {
        dao.deleteFromShoppingCart(rid, recordId);
    }

    public static boolean updateGoodsNumInShoppingCat(String uid, String rid, String goodNum) {
        return dao.updateGoodsNumInShoppingCart(uid, rid, goodNum);
    }

    public static ArrayList<Category> getCategories() {
        return dao.getCategories();
    }

    public static ArrayList<GoodsBrief> getGoodsBriefByKeyword(SearchOption option) {
        return dao.getGoodsBriefByKeyword(option);
    }

    public static ArrayList<GoodsBrief> getGoodsBriefByCategory(SearchOption option) {
        return dao.getGoodsBriefByCategory(option);
    }

    public static ArrayList<GoodsBrief> getGoodsImageByL1(String levelOne, String imageNum) {
        return dao.getGoodsImageByL1(levelOne, imageNum);
    }

    public static String getGoodsNumInSC(String uid) {
        return dao.getGoodsNumInSC(uid);
    }

    public static GoodsDetail getGoodsDetail(String gid) {
        return dao.getGoodsDetail(gid);
    }

    public static ShopBrief getShopBriefInfoByGoodsId(String gid) {
        return dao.getShopBriefInfoByGoodsId(gid);
    }

    public static UserFullInfo getUserInfo(String uid) {
        return dao.getUserInfo(uid);
    }

    public static void updateUserInfo(String uid, String type, String newValue) {
        dao.updateUserInfo(uid, type, newValue);
    }

    public static ArrayList<Receiver> getReceivers(String uid) {
        return dao.getReceivers(uid);
    }

    public static boolean addReceiver(String uid, Receiver r) {
        return dao.addReceiver(uid, r);
    }

    public static boolean modifyReceiver(Receiver r) {
        return dao.modifyReceiver(r);
    }

    public static boolean deleteReceiver(String rid) {
        return dao.deleteReceiver(rid);
    }

    public static OrderDetail getOrderById(String oid) {
        return dao.getOrderById(oid);
    }

    public static ArrayList<FavortiteGoods> getGoodsFavorite(String uid, String nPerPage,
            String pageNum) {
        return dao.getGoodsFavorite(uid, nPerPage, pageNum);
    }

    public static ArrayList<FavoriteShop> getShopFavorite(String uid, String nPerPage,
            String pageNum) {
        return dao.getShopFavorite(uid, nPerPage, pageNum);
    }

    public static void deleteGoodsFromFavorite(String uid, String gid) {
        dao.deleteGoodsFromFavorite(uid, gid);
    }

    public static void deleteShopFromFavorite(String uid, String sid) {
        dao.deleteShopFromFavorite(uid, sid);
    }

    public static void addGoodsToFavorite(String uid, String gid) {
        dao.addGoodsToFavorite(uid, gid);
    }

    public static List<OrderBrief> getOrderByStatus(String uid, String orderStatus,
            String nPerPage, String pageNum) {
        switch (orderStatus) {
        case "*":
            return dao.getOrderByStatus(uid, "%", nPerPage, pageNum);
        case "待付款":
            return dao.getOrderByStatus(uid, "待付款", nPerPage, pageNum);
        case "待收货":
            List<OrderBrief> orders = dao.getOrderByStatus(uid, "待发货", nPerPage, pageNum);
            orders.addAll(dao.getOrderByStatus(uid, "待收货", nPerPage, pageNum));
            return orders;
        case "已完成":
            List<OrderBrief> orders2 = dao.getOrderByStatus(uid, "已完成", nPerPage, pageNum);
            orders2.addAll(dao.getOrderByStatus(uid, "已取消", nPerPage, pageNum));
            return orders2;
        case "待评价":
            return dao.getOrderByStatus(uid, "待评价", nPerPage, pageNum);
        default:
            return dao.getOrderByStatus(uid, "%", nPerPage, pageNum);
        }
    }

    public static boolean updateOrderStatus(String oid, String newStat) {
        return dao.updateOrderStatus(oid, newStat);
    }

    public static List<String> confirmOrders(String uid, List<SCOrder> ordersToConfirm, String rid,
            String payMethod) {
        List<String> orders = new ArrayList<String>();

        for (SCOrder o : ordersToConfirm) {
            orders.add(dao.genOrder(uid, o, rid, payMethod));
        }

        return orders;
    }

    public static int payOrder(List<String> orders) {
        int successNum = 0;
        for (String o : orders) {
            if (dao.updateOrderStatus(o, "待发货") == true) {
                successNum += 1;
            }
        }
        return successNum;
    }

    public static String updateAvatar(String uid, ServletContext servletContext,
            InputStream imageInputStream) {

        String imagedir = servletContext.getInitParameter("imagedir")
                + "/avatars/";

        // 图片名格式：20161123204206613375.jpg。
        // 代表 2016-11-23 20:42:06.613 + 3 位 0 - 9 间随机数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuilder imageName = new StringBuilder(dateFormat.format(new Date()));
        Random random = new Random();
        for (int i = 0; i < 3; ++i) {
            imageName.append(random.nextInt(10));
        }
        imageName.append(".jpg");

        String targetFile = imagedir + imageName;
        try {
            FileUtils.copyInputStreamToFile(imageInputStream, new File(targetFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newAvatarAddr = "images/avatars" + imageName;

        dao.updateAvatar(uid, newAvatarAddr);
        return newAvatarAddr;
    }

}

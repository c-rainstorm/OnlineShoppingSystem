package com.groupnine.oss.user.dao;

import java.util.ArrayList;
import java.util.List;

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

public interface UserDao {

    boolean matchUserPassword(UserLoginInfo info);

    UserSessionInfo getUserSessionInfo(UserLoginInfo info);

    boolean checkUsernameAvil(String username);

    boolean checkPhoneAvil(String phone);

    boolean addNewUser(UserLoginInfo userLoginInfo);

    void addGoodsToShoppingCart(String userId, GoodsItemInSC goods);

    ArrayList<ShoppingCart> getShoppingCart(String userId);

    void deleteFromShoppingCart(String id, String rid);

    boolean updateGoodsNumInShoppingCart(String uid, String rid, String goodNum);

    ArrayList<Category> getCategories();

    ArrayList<GoodsBrief> getGoodsBriefByKeyword(SearchOption option);

    ArrayList<GoodsBrief> getGoodsBriefByCategory(SearchOption option);

    ArrayList<GoodsBrief> getGoodsImageByL1(String levelOne, String imageNum);

    String getGoodsNumInSC(String uid);

    GoodsDetail getGoodsDetail(String goodsId);

    ShopBrief getShopBriefInfoByGoodsId(String gid);

    UserFullInfo getUserInfo(String uid);

    void updateUserInfo(String uid, String type, String newValue);

    ArrayList<Receiver> getReceivers(String uid);

    boolean addReceiver(String uid, Receiver r);

    boolean modifyReceiver(Receiver r);

    boolean deleteReceiver(String rid);

    OrderDetail getOrderById(String oid);

    ArrayList<FavortiteGoods> getGoodsFavorite(String uid, String nPerPage, String pageNum);

    ArrayList<FavoriteShop> getShopFavorite(String uid, String nPerPage, String pageNum);

    void deleteGoodsFromFavorite(String uid, String gid);

    void deleteShopFromFavorite(String uid, String sid);

    void addGoodsToFavorite(String uid, String gid);

    List<OrderBrief> getOrderByStatus(String uid, String orderStatus, String nPerPage,
            String pageNum);

    boolean updateOrderStatus(String oid, String newStat);

    String genOrder(String uid, SCOrder o, String rid, String payMethod);

    void updateAvatar(String uid, String targetFile);
}

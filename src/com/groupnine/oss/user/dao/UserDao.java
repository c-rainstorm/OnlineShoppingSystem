package com.groupnine.oss.user.dao;

import com.groupnine.oss.user.entity.GoodsItemInSC;
import com.groupnine.oss.user.entity.UserLoginInfo;
import com.groupnine.oss.user.entity.UserSessionInfo;

public interface UserDao {

    boolean matchUserPassword(UserLoginInfo info);

    UserSessionInfo getUserSessionInfo(UserLoginInfo info);

    boolean checkUsernameAvil(String username);

    boolean checkPhoneAvil(String phone);

    boolean addNewUser(UserLoginInfo userLoginInfo);

    void addGoodsToShoppingCart(String userId, GoodsItemInSC goods);
}

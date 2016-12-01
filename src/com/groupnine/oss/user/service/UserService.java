package com.groupnine.oss.user.service;

import com.groupnine.oss.user.dao.UserDao;
import com.groupnine.oss.user.dao.UserDaoMySQLImpl;
import com.groupnine.oss.user.entity.GoodsItemInSC;
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
}

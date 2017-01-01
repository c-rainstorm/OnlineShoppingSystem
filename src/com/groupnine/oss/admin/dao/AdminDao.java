package com.groupnine.oss.admin.dao;

import java.util.ArrayList;

import com.groupnine.oss.admin.entity.Admin;
import com.groupnine.oss.admin.entity.Transaction;
import com.groupnine.oss.admin.entity.User;
import com.groupnine.oss.seller.entity.Shop;

public interface AdminDao {

    boolean checkLoginInfo(Admin admin);

    // 获取事务详细信息
    Transaction getTransactionDetail(Transaction transaction);

    // 更新事务
    boolean updatetransation(Transaction transaction);

    boolean DeleteUser(int uid);

    boolean DeleteShop(String phone);

    ArrayList<Transaction> GetUnsolvedTransaction();

    ArrayList<Transaction> GetDoneTransaction();

    ArrayList<Transaction> SearchTransaction(int transactionId, String comment);

    ArrayList<User> GetUserInfo(String phone, String username);

    ArrayList<Shop> GetShopInfo(String phone, String shop_name);

}

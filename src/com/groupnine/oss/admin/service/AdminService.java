package com.groupnine.oss.admin.service;

import java.util.ArrayList;

import com.groupnine.oss.admin.entity.Admin;
import com.groupnine.oss.admin.entity.Transaction;
import com.groupnine.oss.admin.entity.User;
import com.groupnine.oss.seller.entity.Shop;

public interface AdminService {

    boolean checkLoginInfo(Admin admin);

    Transaction getTransactionDetail(Transaction transaction);

    public boolean updatetransation(Transaction transaction);

    boolean DeleteUser(int uid);

    boolean DeleteShop(String phone);

    ArrayList<Transaction> GetUnsolvedTransaction();

    ArrayList<Transaction> GetDoneTransaction();

    ArrayList<Transaction> SearchTransaction(int transactionId, String comment);

    ArrayList<Shop> GetShopInfo(String phone, String shop_name);

    ArrayList<User> GetUserInfo(String phone, String username);
}

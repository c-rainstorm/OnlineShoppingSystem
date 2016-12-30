package com.groupnine.oss.admin.service;

import java.util.ArrayList;

import com.groupnine.oss.admin.dao.AdminDao;
import com.groupnine.oss.admin.dao.AdminDaoImpl;
import com.groupnine.oss.admin.entity.Admin;
import com.groupnine.oss.admin.entity.Transaction;
import com.groupnine.oss.admin.entity.User;
import com.groupnine.oss.seller.entity.Shop;

public class AdminServiceImpl implements AdminService {

    private AdminDao dao = new AdminDaoImpl();

    @Override
    public boolean checkLoginInfo(Admin admin) {
        return dao.checkLoginInfo(admin);
    }

    @Override
    public Transaction getTransactionDetail(Transaction transaction) {
        return dao.getTransactionDetail(transaction);
    }

    @Override
    public boolean updatetransation(Transaction transaction) {
        return dao.updatetransation(transaction);

    }

    @Override
    public boolean DeleteUser(int uid) {

        return dao.DeleteUser(uid);
    }

    @Override
    public boolean DeleteShop(String phone) {

        return dao.DeleteShop(phone);
    }

    @Override
    public ArrayList<Transaction> GetUnsolvedTransaction() {

        return dao.GetUnsolvedTransaction();
    }

    @Override
    public ArrayList<Transaction> GetDoneTransaction() {

        return dao.GetDoneTransaction();

    }

    @Override
    public ArrayList<Transaction> SearchTransaction(int transactionId, String comment) {

        return dao.SearchTransaction(transactionId, comment);
    }

    @Override
    public ArrayList<Shop> GetShopInfo(String phone, String shop_name) {
        return dao.GetShopInfo(phone, shop_name);

    }

    @Override
    public ArrayList<User> GetUserInfo(String phone, String username) {
        return dao.GetUserInfo(phone, username);
    }
}

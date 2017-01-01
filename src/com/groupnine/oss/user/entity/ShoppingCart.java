package com.groupnine.oss.user.entity;

import java.util.ArrayList;

/*
 * 一个店铺算一个购物车 所以，用户所看到的购物车其实是多个购物车，每个购物车分别属于不同的店铺
 */

public class ShoppingCart {
    private String shopId;
    private String shopName;
    private ArrayList<GoodsItemInSC> goodsInThisShop = new ArrayList<>();

    // Constructor

    public ShoppingCart(String id, String name) {
        shopId = id;
        shopName = name;
    }

    // Getters

    public String getShopId() {
        return shopId;
    }

    public ArrayList<GoodsItemInSC> getGoodsInThisShop() {
        return goodsInThisShop;
    }

    // Setters

    public void setShopId(String id) {
        shopId = id;
    }

    public void setShopName(String name) {
        shopName = name;
    }

    public void addToThisSC(GoodsItemInSC g) {
        goodsInThisShop.add(g);
    }
}

package com.groupnine.oss.user.entity;

public class OrderBrief {
    public String orderId;
    public String shopName;
    public String orderStatus;

    // constructor

    public OrderBrief(String id, String name, String status) {
        orderId = id;
        shopName = name;
        orderStatus = status;
    }
}

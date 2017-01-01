package com.groupnine.oss.user.entity;

import java.util.ArrayList;

public class OrderDetail {
    public String orderId;
    public String trackingNumber;
    public String orderStatus;
    public String username;
    public OrderReceiver receiver = new OrderReceiver();
    public ArrayList<GoodsInOrder> goodsInOrder = new ArrayList();
    public String payMethod;
    public String orderTime;
    public String completeTime;
    public String annotation;
    public String total;
}

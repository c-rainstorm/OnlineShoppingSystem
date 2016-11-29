package com.groupnine.oss.pub.entity;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.groupnine.oss.user.entity.Receiver;

public class Order {
    private String orderId;         // 16 位数字,含前导 0
    private int userId;
    private int shopId;
    private Receiver receiver;
    private ArrayList<GoodsInOrder> goodsInOrder;
    private String orderStatus;
    private String trackingNumber;
    private String payMethod;
    private Timestamp orderTime;
    private Timestamp completeTime;
    private String annotation;
    private double total;

    public long getOrderId() {
        return Long.parseLong(orderId);
    }

    public void setOrderId(long orderId) {
        StringBuilder builder = new StringBuilder(20);
        builder.append("0000000000000000");
        String temp = Long.toString(orderId);
        builder.insert(builder.length() - temp.length() + 1, temp);
        this.orderId = builder.toString();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public ArrayList<GoodsInOrder> getGoodsInOrder() {
        return goodsInOrder;
    }

    public void setGoodsInOrder(ArrayList<GoodsInOrder> goodsInOrder) {
        this.goodsInOrder = goodsInOrder;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Timestamp getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Timestamp completeTime) {
        this.completeTime = completeTime;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}

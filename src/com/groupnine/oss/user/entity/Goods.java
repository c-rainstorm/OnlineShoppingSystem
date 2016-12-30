package com.groupnine.oss.user.entity;

public class Goods {
    public static boolean isRecordIdWellFormed(String id) {
        return (id != null && id.matches("[0-9]+"));
    }

    public static boolean isGoodsIdWellFormed(String id) {
        return (id != null && id.matches("[0-9]+"));
    }

    public static boolean isGoodsAttributeIdWellFormed(String id) {
        return (id != null && id.matches("[0-9]+"));
    }

    public static boolean isGoodsNumWellFormed(String n) {
        return (n != null && n.matches("[1-9][0-9]*"));
    }
}
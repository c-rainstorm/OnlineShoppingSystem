package com.groupnine.oss.admin.entity;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int userId;
    private int adminId;
    private String transactionType;
    private String transactionStatus;
    private String comment;
    private Timestamp commitTime;
    private Timestamp completeTime;
    private String annotation;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Timestamp commitTime) {
        this.commitTime = commitTime;
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

}

package com.groupnine.oss.util;

import java.util.ArrayList;

public class Page<T> {
    private String pageContent;
    private int pageNum = 1; // 标记当前要获取第几页的数据
    private int maxItemNum = 10; // 一次最多显示的项数
    private int actualItemNum; // 实际获取的项目数

    private ArrayList<T> items;

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getMaxItemNum() {
        return maxItemNum;
    }

    public void setMaxItemNum(int maxItemNum) {
        this.maxItemNum = maxItemNum;
    }

    public int getActualItemNum() {
        return actualItemNum;
    }

    public void setActualItemNum(int actualItemNum) {
        this.actualItemNum = actualItemNum;
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }

}

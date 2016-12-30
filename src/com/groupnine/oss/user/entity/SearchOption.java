package com.groupnine.oss.user.entity;

public class SearchOption {
    public String levelOne;
    public String levelTwo;
    public String keyword;
    public String nPerPage;
    public String pageNum;
    public String sortByPrice;
    public String priceUp;

    // Setters

    public SearchOption setLevelOne(String levelOne) {
        this.levelOne = levelOne;
        return this;
    }

    public SearchOption setLevelTwo(String levelTwo) {
        this.levelTwo = levelTwo;
        return this;
    }

    public SearchOption setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public SearchOption setnPerPage(String nPerPage) {
        this.nPerPage = nPerPage;
        return this;
    }

    public SearchOption setPageNum(String n) {
        this.pageNum = n;
        return this;
    }

    public SearchOption setSortByPrice(String sortByPrice) {
        this.sortByPrice = sortByPrice;
        return this;
    }

    public SearchOption setPriceUp(String priceUp) {
        this.priceUp = priceUp;
        return this;
    }

    @Override
    public String toString() {
        return "SearchOption [levelOne=" + levelOne + ", levelTwo=" + levelTwo + ", keyword="
                + keyword + ", nPerPage=" + nPerPage + ", pageNum=" + pageNum + ", sortByPrice="
                + sortByPrice + ", priceUp=" + priceUp + "]";
    }

}

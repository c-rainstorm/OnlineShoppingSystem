package com.groupnine.oss.user.entity;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<String> levelTwo = new ArrayList<>();

    // default empty constructor

    // Getters

    public String getName() {
        return name;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void addToSubLevel(String name) {
        levelTwo.add(name);
    }
}

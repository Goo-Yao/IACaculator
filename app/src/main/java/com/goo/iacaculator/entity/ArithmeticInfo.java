package com.goo.iacaculator.entity;

/**
 * Created by Goo on 2016-10-26.
 */

public class ArithmeticInfo {
    private String name;//名字
    private String bgColor;//背景颜色
    private String description;//描述

    public ArithmeticInfo(String name, String bgColor, String description) {
        this.name = name;
        this.bgColor = bgColor;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

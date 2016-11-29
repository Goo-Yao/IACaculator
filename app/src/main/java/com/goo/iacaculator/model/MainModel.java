package com.goo.iacaculator.model;

import com.goo.iacaculator.entity.ArithmeticInfo;
import com.goo.iacaculator.model.mInterface.MainMInterface;

import java.util.ArrayList;

/**
 * Created by Goo on 2016-10-26.
 */

public class MainModel implements MainMInterface {
    @Override
    public ArrayList<ArithmeticInfo> getAMData() {
        ArrayList<ArithmeticInfo> data = new ArrayList<>();
        data.add(new ArithmeticInfo("PlayFair", "#FBC02D", "对称式密码，双字母取代加密"));
        data.add(new ArithmeticInfo("Hill", "#536DFE", "运用基本矩阵论原理的替换密码"));
        data.add(new ArithmeticInfo("RSA", "#F44336", "第一个非对称加密算法"));
        data.add(new ArithmeticInfo("Caesar", "#8BC34A", "通过排列明文和密文字母表加密"));
        data.add(new ArithmeticInfo("More", "#9C27B0", "待实现..."));
        return data;
    }
}

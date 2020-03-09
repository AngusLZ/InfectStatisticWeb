package com.hack.infect_support.dto;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class ProvinceCut {
//    省的名称
    private String name;
//    确证人数
    private int confirmedCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }
}

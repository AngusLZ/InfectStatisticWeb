package com.hack.infect_support.domain;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class ProvinceCut {
//    省的名称
    private String name;

//    现存确证人数
    private int currentConfirmedCount;

    public int getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(int currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

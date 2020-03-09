package com.hack.infect_support.dto;

import java.awt.*;
import java.util.List;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class Province extends ProvinceCut {
//    疑似病例
    private int suspectedCount;
//    治愈病例
    private int curedCount;
//    死亡病例
    private int deadCount;
//    较昨日的确证人数
    private int confirmedIncr;
//    较昨日的疑似病例
    private int suspectedIncr;
//    较昨日的治愈病例
    private int curedIncr;
//    较昨日的死亡病例
    private int deadIncr;
    //
    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public int getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(int suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public int getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(int curedCount) {
        this.curedCount = curedCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public int getConfirmedIncr() {
        return confirmedIncr;
    }

    public void setConfirmedIncr(int confirmedIncr) {
        this.confirmedIncr = confirmedIncr;
    }

    public int getSuspectedIncr() {
        return suspectedIncr;
    }

    public void setSuspectedIncr(int suspectedIncr) {
        this.suspectedIncr = suspectedIncr;
    }

    public int getCuredIncr() {
        return curedIncr;
    }

    public void setCuredIncr(int curedIncr) {
        this.curedIncr = curedIncr;
    }

    public int getDeadIncr() {
        return deadIncr;
    }

    public void setDeadIncr(int deadIncr) {
        this.deadIncr = deadIncr;
    }
}

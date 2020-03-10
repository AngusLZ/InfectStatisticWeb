package com.hack.infect_support.dto;

import java.awt.*;
import java.util.List;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class Province extends ProvinceCut {
//    确证人数
    private int confirmedCount;
//    疑似病例
    private int suspectedCount;
//    治愈病例
    private int curedCount;
//    死亡病例
    private int deadCount;
//    相比昨天的现存确证人数
    private int currentConfirmedIncr;
//    较昨日的累计确证人数
    private int confirmedIncr;
//    较昨日的疑似病例
    private int suspectedIncr;
//    较昨日的治愈病例
    private int curedIncr;
//    较昨日的死亡病例
    private int deadIncr;
    //
    private List<City> cityList;

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public int getCurrentConfirmedIncr() {
        return currentConfirmedIncr;
    }

    public void setCurrentConfirmedIncr(int currentConfirmedIncr) {
        this.currentConfirmedIncr = currentConfirmedIncr;
    }

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

    @Override
    public String toString() {
        return "Province{" +
                "suspectedCount=" + suspectedCount +
                ", curedCount=" + curedCount +
                ", deadCount=" + deadCount +
                ", confirmedIncr=" + confirmedIncr +
                ", suspectedIncr=" + suspectedIncr +
                ", curedIncr=" + curedIncr +
                ", deadIncr=" + deadIncr +
                ", cityList=" + cityList +
                '}';
    }
}

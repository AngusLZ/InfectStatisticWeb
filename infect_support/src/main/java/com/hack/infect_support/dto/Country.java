package com.hack.infect_support.dto;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class Country {
//    现存确证人数
    private int currentConfirmedCount;
//    累计确证人数
    private int confirmedCount;
//    现存疑似人数
    private int suspectedCount;
//    累计治愈人数
    private int curedCount;
//    累计死亡人数
    private int deadCount;
//    现存重症人数
    private int seriousCount;
//    相比昨天的现存确证人数
    private int currentConfirmedIncr;
//    相比昨天的累计确证人数
    private int confirmedIncr;
//    新增的疑似人数
    private int suspectedIncr;
//    相比昨天新增治愈人数
    private int curedIncr;
//    相比昨天新增死亡人数
    private int deadIncr;
//    相比昨天新增重症人数
    private int seriousIncr;

    public int getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(int currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
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

    public int getSeriousCount() {
        return seriousCount;
    }

    public void setSeriousCount(int seriousCount) {
        this.seriousCount = seriousCount;
    }

    public int getCurrentConfirmedIncr() {
        return currentConfirmedIncr;
    }

    public void setCurrentConfirmedIncr(int currentConfirmedIncr) {
        this.currentConfirmedIncr = currentConfirmedIncr;
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

    public int getSeriousIncr() {
        return seriousIncr;
    }

    public void setSeriousIncr(int seriousIncr) {
        this.seriousIncr = seriousIncr;
    }

    @Override
    public String toString() {
        return "Country{" +
                "currentConfirmedCount=" + currentConfirmedCount +
                ", confirmedCount=" + confirmedCount +
                ", suspectedCount=" + suspectedCount +
                ", curedCount=" + curedCount +
                ", deadCount=" + deadCount +
                ", seriousCount=" + seriousCount +
                ", currentConfirmedIncr=" + currentConfirmedIncr +
                ", confirmedIncr=" + confirmedIncr +
                ", suspectedIncr=" + suspectedIncr +
                ", curedIncr=" + curedIncr +
                ", deadIncr=" + deadIncr +
                ", seriousIncr=" + seriousIncr +
                '}';
    }
}

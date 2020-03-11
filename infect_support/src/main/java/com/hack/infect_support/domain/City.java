package com.hack.infect_support.domain;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class City {
    private String name;
    //    现存确证人数
    private int currentConfirmedCount;
    //    累计确证人数
    private int confirmedCount;
    //    现存疑似人数
    private int suspectedCount;
    //    累计治愈人数
    private int curedCount;

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", currentConfirmedCount=" + currentConfirmedCount +
                ", confirmedCount=" + confirmedCount +
                ", suspectedCount=" + suspectedCount +
                ", curedCount=" + curedCount +
                ", deadCount=" + deadCount +
                '}';
    }

    //    累计死亡人数
    private int deadCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}

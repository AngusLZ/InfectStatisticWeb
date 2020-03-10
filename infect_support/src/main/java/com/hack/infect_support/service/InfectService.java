package com.hack.infect_support.service;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public interface InfectService {
    String getCountry(String date);

    String getAllProvince(String date);

    String getSpecialProvince(String info);

    String getCities(String info);

    String ImgInfo(String info);
}

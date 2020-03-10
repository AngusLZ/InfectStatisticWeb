package com.hack.infect_support.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hack.infect_support.dto.City;
import com.hack.infect_support.dto.Country;
import com.hack.infect_support.dto.Province;
import com.hack.infect_support.dto.ProvinceCut;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class Test {

    public void getAll(){
        String now = new DateGet().getNow();
        String yesterday = new DateGet().getDay(now , -1);
//        String d = new DateGet().getDay(now , -1);
        String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
        String jsonResult = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + now);
        String jsonResultY = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + yesterday);
        JSONObject jsonObject = JSON.parseObject(jsonResult);
        JSONObject jsonObjectY = JSON.parseObject(jsonResultY);
//        System.out.println(jsonResult);
        Object o = jsonObject.get("newslist");
        Object oY = jsonObjectY.get("newslist");
//        System.out.println(o);
        JSONArray jsonArray = JSON.parseArray(o+"");
        JSONArray jsonArrayY = JSON.parseArray(oY+"");
        List<Province> provinces = new LinkedList<Province>();
        for (int i = 0 ; i < jsonArray.size() ; i++){
            List<City> cities = new LinkedList<City>();
            Object n1 = jsonArray.get(i);
            Object n1Y = jsonArrayY.get(i);
            JSONObject jsonObject1 = JSON.parseObject(n1+"");
            JSONObject jsonObject1Y = JSON.parseObject(n1Y+"");

//            getCities
            Object n2 = jsonObject1.get("cities");
            JSONArray jsonArray1 = JSON.parseArray(n2+"");
            for (int j = 0 ; j < jsonArray1.size() ; j ++){
                Object n3 = jsonArray1.get(j);
                JSONObject jsonObject2 = JSON.parseObject(n3+"");
//                System.out.println(jsonObject2);
                City city = new City();
                city.setName(String.valueOf(jsonObject2.get("cityName")));
                city.setCurrentConfirmedCount((Integer) jsonObject2.get("currentConfirmedCount"));
                city.setConfirmedCount((Integer) jsonObject2.get("confirmedCount"));
                city.setSuspectedCount((Integer) jsonObject2.get("suspectedCount"));
                city.setCuredCount((Integer) jsonObject2.get("curedCount"));
                city.setDeadCount((Integer) jsonObject2.get("deadCount"));
                cities.add(city);
            }
            Province province = new Province();

            Object n3 = jsonObject1.get("currentConfirmedCount");
            Object n4 = jsonObject1Y.get("currentConfirmedCount");

            province.setName(String.valueOf(jsonObject1.get("provinceShortName")));
            province.setCurrentConfirmedCount((Integer) jsonObject1.get("currentConfirmedCount"));
            province.setConfirmedCount((Integer) jsonObject1.get("confirmedCount"));
            province.setSuspectedCount((Integer) jsonObject1.get("suspectedCount"));
            province.setCuredCount((Integer) jsonObject1.get("curedCount"));
            province.setDeadCount((Integer) jsonObject1.get("deadCount"));

            province.setCurrentConfirmedIncr(((Integer)jsonObject1.get("currentConfirmedCount")) - (Integer) jsonObject1Y.get("currentConfirmedCount"));
            province.setConfirmedIncr((Integer) jsonObject1.get("confirmedCount") - (Integer) jsonObject1Y.get("confirmedCount"));
            province.setSuspectedIncr((Integer) jsonObject1.get("suspectedCount") - (Integer) jsonObject1Y.get("suspectedCount"));
            province.setCuredIncr((Integer) jsonObject1.get("curedCount") - (Integer) jsonObject1Y.get("curedCount"));
            province.setDeadIncr((Integer) jsonObject1.get("deadCount") - (Integer) jsonObject1Y.get("deadCount"));
            province.setCityList(cities);
            provinces.add(province);
        }

        String info = JSON.toJSONString(provinces);
        System.out.println(info);
    }





    public static void main(String[] args){
//        new Test().getCountry(new DateGet().getNow());
//        new Test().getAllProvince();
        new Test().getAll();
//        System.out.println(new DateGet().getNow());
//        new Test().T();
    }
}

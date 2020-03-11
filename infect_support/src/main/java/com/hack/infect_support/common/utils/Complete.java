package com.hack.infect_support.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hack.infect_support.domain.Country;
import com.hack.infect_support.domain.ProvinceCut;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class Complete {
    public Country completeCountry(Country country){
        String httpUrl = "http://api.tianapi.com/txapi/ncov/index";
        String jsonResult = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567");
//        System.out.println(jsonResult);

        JSONObject jsonObject = JSON.parseObject(jsonResult);
        Object j = jsonObject.get("newslist");
//        System.out.println(j);
        JSONArray jsonArray = JSON.parseArray(j+"");
        Object n1 = jsonArray.get(0);
//        System.out.println("n1: " + n1);
        JSONObject jsonObject1 = JSON.parseObject(n1+"");
        Object n2 = jsonObject1.get("desc");
//        System.out.println("n2: " + n2);
        JSONObject jsonObject2 = JSON.parseObject(n2 + "");
        country.setConfirmedCount((Integer) jsonObject2.get("confirmedCount"));
        country.setConfirmedIncr((Integer) jsonObject2.get("confirmedIncr"));
        country.setCuredCount((Integer) jsonObject2.get("curedCount"));
        country.setCuredIncr((Integer) jsonObject2.get("curedIncr"));
        country.setCurrentConfirmedCount((Integer) jsonObject2.get("currentConfirmedCount"));
        country.setCurrentConfirmedIncr((Integer) jsonObject2.get("currentConfirmedIncr"));
        country.setDeadCount((Integer) jsonObject2.get("deadCount"));
        country.setDeadIncr((Integer) jsonObject2.get("deadIncr"));
        country.setSeriousCount((Integer) jsonObject2.get("seriousCount"));
        country.setSeriousIncr((Integer) jsonObject2.get("seriousIncr"));
        country.setSuspectedCount((Integer) jsonObject2.get("suspectedCount"));
        country.setSuspectedIncr((Integer) jsonObject2.get("suspectedIncr"));
        return country;
    }

    public List<ProvinceCut> setProvinces(String name , String date){
        String httpUrl = "http://api.tianapi.com/txapi/ncov/index";
        String jsonResult = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567");



        List<ProvinceCut> provinceCuts = new LinkedList<>();
        return provinceCuts;
    }
}

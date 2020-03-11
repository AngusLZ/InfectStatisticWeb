package com.hack.infect_support.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hack.infect_support.domain.City;
import com.hack.infect_support.useless.A;

import java.util.*;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class Test {
    public void f(){
        String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
        String jsonResult = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + "2020-03-10");
        JSONObject jsonObject = JSON.parseObject(jsonResult);
//        System.out.println(jsonResult);
        Object o = jsonObject.get("newslist");
//        System.out.println(o);
        JSONArray jsonArray = JSON.parseArray(o+"");

        List<City> cities = new LinkedList<City>();
        for (int i = 0 ; i < jsonArray.size() ; i++) {
            Object n1 = jsonArray.get(i);
            JSONObject jsonObject1 = JSON.parseObject(n1+"");
            System.out.println(jsonObject1.get("provinceShortName"));
        }
    }

    public void getAll(){
        String now = new DateGet().getNow();
        List<Integer> integers = new LinkedList<Integer>();
        HashMap<String , String> provincesHashmap = new HashMap<>();
        HashMap<String , String> countryHashmap = new HashMap<>();
        for (int j = 0 ; j < 20 ; j++) {
            String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
            String jsonResult = new Info().request(httpUrl, "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + now);
            provincesHashmap.put(now , jsonResult);

            String httpUrlC = "http://api.tianapi.com/txapi/ncov/index";
            String jsonResultC = new Info().request(httpUrlC , "key=c4ca7b7ef10ab54850c72e72e7693567&date="+now);
            countryHashmap.put(now ,jsonResultC);

            now = new DateGet().getDay(now , -1);
        }
        new EasyDb().provinceHashMap = provincesHashmap;
        new EasyDb().countryHashMap = countryHashmap;
    }





    public static void main(String[] args){
//        new Test().getCountry(new DateGet().getNow());
//        new Test().getAllProvince();
        new Test().getAll();
        new A().aa();
//        System.out.println(new DateGet().getNow());
//        new Test().T();
        new Test().f();
    }
}

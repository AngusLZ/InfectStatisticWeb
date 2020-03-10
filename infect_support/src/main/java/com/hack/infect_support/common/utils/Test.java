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
        List<Integer> integers = new LinkedList<Integer>();
        for (int j = 0 ; j < 30 ; j++) {
            String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
            String jsonResult = new Info().request(httpUrl, "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + now);

//            String httpUrl = "http://api.tianapi.com/txapi/ncov/index";
//            String jsonResult = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567&date="+now);

            System.out.println(now);
            System.out.println(jsonResult);
            now = new DateGet().getDay(now , -1);
        }
    }





    public static void main(String[] args){
//        new Test().getCountry(new DateGet().getNow());
//        new Test().getAllProvince();
        new Test().getAll();
//        System.out.println(new DateGet().getNow());
//        new Test().T();
    }
}

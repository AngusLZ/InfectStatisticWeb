package com.hack.infect_support.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hack.infect_support.common.utils.DateGet;
import com.hack.infect_support.common.utils.Info;
import com.hack.infect_support.domain.*;
import com.hack.infect_support.service.InfectService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

@Service
public class InfectServiceImpl implements InfectService {
    public HashMap<String , String> countryHashMap = new HashMap<>();
    public HashMap<String , String> provincesHashMap = new HashMap<>();

    public void setDb(){
        String now = new DateGet().getNow();
        if (countryHashMap.size() == 0 || provincesHashMap.size() == 0) {
            countryHashMap.clear(); provincesHashMap.clear();
            for (int j = 0; j < 20; j++) {
                String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
                String jsonResult = new Info().request(httpUrl, "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + now);
                provincesHashMap.put(now, jsonResult);

                String httpUrlC = "http://api.tianapi.com/txapi/ncov/index";
                String jsonResultC = new Info().request(httpUrlC, "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + now);
                countryHashMap.put(now, jsonResultC);

                now = new DateGet().getDay(now, -1);
            }
        }
        System.out.println("set small DB success");
    }

//    获得国家级别的疫情信息
    public String getCountry(String date){
//        String httpUrl = "http://api.tianapi.com/txapi/ncov/index";
//        String jsonResult = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567&date="+date);
        String jsonResult = countryHashMap.get(date);
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
        Country country = new Country();
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

        String jsonString = JSON.toJSONString(country);

        return jsonString;
    }
//    获得各省具体的确证数量
    public String getAllProvince(String date) {
//        String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
//        String jsonResult = new Info().request(httpUrl, "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + date);
        String jsonResult = provincesHashMap.get(date);
        JSONObject jsonObject = JSON.parseObject(jsonResult);
//        System.out.println(jsonResult);
        Object o = jsonObject.get("newslist");
//        System.out.println(o);
        JSONArray jsonArray = JSON.parseArray(o + "");
        List<ProvinceCut> provinceCuts = new LinkedList<ProvinceCut>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Object n1 = jsonArray.get(i);
            JSONObject jsonObject1 = JSON.parseObject(n1 + "");
            ProvinceCut provinceCut = new ProvinceCut();
            provinceCut.setName(String.valueOf(jsonObject1.get("provinceShortName")));
            provinceCut.setCurrentConfirmedCount(Integer.parseInt(String.valueOf(jsonObject1.get("currentConfirmedCount"))));
            provinceCuts.add(provinceCut);
        }
        String js = JSON.toJSONString(provinceCuts);
        return js;
    }
//    获得各省的累计确证数
    public String getProvinceConfirmed(String date){
//        String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
//        String jsonResult = new Info().request(httpUrl, "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + date);
        String jsonResult = provincesHashMap.get(date);
        JSONObject jsonObject = JSON.parseObject(jsonResult);
//        System.out.println(jsonResult);
        Object o = jsonObject.get("newslist");
//        System.out.println(o);
        JSONArray jsonArray = JSON.parseArray(o + "");
        List<ProvinceCut> provinceCuts = new LinkedList<ProvinceCut>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Object n1 = jsonArray.get(i);
            JSONObject jsonObject1 = JSON.parseObject(n1 + "");
            ProvinceCut provinceCut = new ProvinceCut();
            provinceCut.setName(String.valueOf(jsonObject1.get("provinceShortName")));
            provinceCut.setCurrentConfirmedCount(Integer.parseInt(String.valueOf(jsonObject1.get("confirmedCount"))));
            provinceCuts.add(provinceCut);
        }
        String js = JSON.toJSONString(provinceCuts);
        return js;
    }
//    获得每个省的具体信息
    public String getSpecialProvince(String info){
        JSONObject object = JSON.parseObject(info);
        String name = String.valueOf(object.get("name"));
        String date = String.valueOf(object.get("date"));

        String yesterday = new DateGet().getDay(date , -1);

//        String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
//        String jsonResult = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + date);
//        String jsonResultY = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + yesterday);
        String jsonResult = provincesHashMap.get(date);
        String jsonResultY = provincesHashMap.get(yesterday);
        JSONObject jsonObject = JSON.parseObject(jsonResult);
        JSONObject jsonObjectY = JSON.parseObject(jsonResultY);
//        System.out.println(jsonResult);
        Object o = jsonObject.get("newslist");
        Object oY = jsonObjectY.get("newslist");
//        System.out.println(o);
        JSONArray jsonArray = JSON.parseArray(o+"");
        JSONArray jsonArrayY = JSON.parseArray(oY+"");
        Province province = new Province();
        for (int i = 0 ; i < jsonArray.size() ; i++){
            Object n1 = jsonArray.get(i);
            Object n1Y = jsonArrayY.get(i);
            JSONObject jsonObject1 = JSON.parseObject(n1+"");
            JSONObject jsonObject1Y = JSON.parseObject(n1Y+"");


            if (String.valueOf(jsonObject1.get("provinceShortName")).equals(name)) {

                province.setName(String.valueOf(jsonObject1.get("provinceShortName")));
                province.setCurrentConfirmedCount((Integer) jsonObject1.get("currentConfirmedCount"));
                province.setConfirmedCount((Integer) jsonObject1.get("confirmedCount"));
                province.setSuspectedCount((Integer) jsonObject1.get("suspectedCount"));
                province.setCuredCount((Integer) jsonObject1.get("curedCount"));
                province.setDeadCount((Integer) jsonObject1.get("deadCount"));

                province.setCurrentConfirmedIncr(((Integer) jsonObject1.get("currentConfirmedCount")) - (Integer) jsonObject1Y.get("currentConfirmedCount"));
                province.setConfirmedIncr((Integer) jsonObject1.get("confirmedCount") - (Integer) jsonObject1Y.get("confirmedCount"));
                province.setSuspectedIncr((Integer) jsonObject1.get("suspectedCount") - (Integer) jsonObject1Y.get("suspectedCount"));
                province.setCuredIncr((Integer) jsonObject1.get("curedCount") - (Integer) jsonObject1Y.get("curedCount"));
                province.setDeadIncr((Integer) jsonObject1.get("deadCount") - (Integer) jsonObject1Y.get("deadCount"));
                break;
            }
        }
        String infoo = JSON.toJSONString(province);
        return infoo;
    }
//    获得省下面的市的信息
    public String getCities(String info){
        JSONObject object = JSON.parseObject(info);
        String name = String.valueOf(object.get("name"));
        String date = String.valueOf(object.get("date"));

//        String d = new DateGet().getDay(now , -1);
//        String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
//        String jsonResult = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + date);
        String jsonResult = provincesHashMap.get(date);
        JSONObject jsonObject = JSON.parseObject(jsonResult);
//        System.out.println(jsonResult);
        Object o = jsonObject.get("newslist");
//        System.out.println(o);
        JSONArray jsonArray = JSON.parseArray(o+"");

        List<City> cities = new LinkedList<City>();
        for (int i = 0 ; i < jsonArray.size() ; i++){
            Object n1 = jsonArray.get(i);
            JSONObject jsonObject1 = JSON.parseObject(n1+"");

            if (String.valueOf(jsonObject1.get("provinceShortName")).equals(name)) {
//            getCities
                Object n2 = jsonObject1.get("cities");
                JSONArray jsonArray1 = JSON.parseArray(n2 + "");
                for (int j = 0; j < jsonArray1.size(); j++) {
                    Object n3 = jsonArray1.get(j);
                    JSONObject jsonObject2 = JSON.parseObject(n3 + "");
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
            }

        }

        String infoo = JSON.toJSONString(cities);

        return infoo;
    }
    //    省和国家的数据实现
    public String ImgInfo(String info){
        JSONObject object = JSON.parseObject(info);
        String name = String.valueOf(object.get("name"));
        String result = null;

        if (!name.equals("全国")) {
            result = getProvinceNumbers(name);
        }else {
            result = getCountryNumbers();
        }
        return result;
    }
//    省份的二十天以内数据
//    !!!数据是从今天往后面倒推
    public String getProvinceNumbers(String name){
        String now = new DateGet().getNow();
        List<ImgInfo> imgInfos = new LinkedList<ImgInfo>();
        for (int j = 0 ; j < 20 ; j++) {
//            String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
//            String jsonResult = new Info().request(httpUrl, "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + now);
            String jsonResult = provincesHashMap.get(now);
            JSONObject jsonObject = JSON.parseObject(jsonResult);
            Object o = jsonObject.get("newslist");
            JSONArray jsonArray = JSON.parseArray(o+"");
//            System.out.println(now);
            for (int i = 0 ; i < jsonArray.size() ; i++){
                Object n1 = jsonArray.get(i);
                JSONObject jsonObject1 = JSON.parseObject(n1+"");
                if (String.valueOf(jsonObject1.get("provinceShortName")).equals(name)) {
                    ImgInfo imgInfo = new ImgInfo();
                    imgInfo.setDate(now);
                    imgInfo.setCurrentConfirmedCount((Integer) jsonObject1.get("currentConfirmedCount"));
                    imgInfo.setCuredCount((Integer) jsonObject1.get("curedCount"));
                    imgInfo.setSuspectedCount((Integer) jsonObject1.get("suspectedCount"));
                    imgInfo.setDeadCount((Integer) jsonObject1.get("deadCount"));
                    imgInfos.add(imgInfo);
                    break;
                }
            }
            now = new DateGet().getDay(now , -1);
        }
        String info = JSON.toJSONString(imgInfos);
        return info;
    }
//    国家的二十天以内的数据
    public String getCountryNumbers(){
        String now = new DateGet().getNow();
        List<ImgInfo> imgInfos = new LinkedList<>();
        for (int i = 0 ; i < 20 ; i++) {
//            String httpUrl = "http://api.tianapi.com/txapi/ncov/index";
//            String jsonResult = new Info().request(httpUrl, "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + now);
            String jsonResult = countryHashMap.get(now);

            JSONObject jsonObject = JSON.parseObject(jsonResult);
            Object j = jsonObject.get("newslist");
            JSONArray jsonArray = JSON.parseArray(j + "");
            Object n1 = jsonArray.get(0);

            JSONObject jsonObject1 = JSON.parseObject(n1 + "");
            Object n2 = jsonObject1.get("desc");

            JSONObject jsonObject2 = JSON.parseObject(n2 + "");
            ImgInfo imgInfo = new ImgInfo();
            imgInfo.setDate(now);
            imgInfo.setCurrentConfirmedCount((Integer) jsonObject2.get("currentConfirmedCount"));
            imgInfo.setCuredCount((Integer) jsonObject2.get("curedCount"));
            imgInfo.setSuspectedCount((Integer) jsonObject2.get("suspectedCount"));
            imgInfo.setDeadCount((Integer) jsonObject2.get("deadCount"));
            imgInfos.add(imgInfo);

            now = new DateGet().getDay(now , -1);
        }
        String jsonString = JSON.toJSONString(imgInfos);

        return jsonString;
    }

}

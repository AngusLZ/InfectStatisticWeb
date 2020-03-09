package com.hack.infect_support.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hack.infect_support.common.utils.Info;
import com.hack.infect_support.dto.Country;
import com.hack.infect_support.dto.ProvinceCut;
import com.hack.infect_support.service.InfectService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

@Service
public class InfectServiceImpl implements InfectService {
//    获得国家级别的疫情信息
    public String getCountry(String date){
        String httpUrl = "http://api.tianapi.com/txapi/ncov/index";
        String jsonResult = new Info().request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567&date="+date);
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
//    获得各省市具体的确证数量
    public String getAllProvince(String date) {
        String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
        String jsonResult = new Info().request(httpUrl, "key=c4ca7b7ef10ab54850c72e72e7693567&date=" + date);
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
//
}

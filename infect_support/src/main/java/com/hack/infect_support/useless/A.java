package com.hack.infect_support.useless;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hack.infect_support.common.utils.EasyDb;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

public class A {

    public void aa(){
        System.out.println(EasyDb.provinceHashMap);
        System.out.println("分割线");
        System.out.println(EasyDb.countryHashMap);
    }

    public static void main(String[] args) {
        String httpUrl = "http://api.tianapi.com/txapi/ncov/index";
        String jsonResult = request(httpUrl , "key=c4ca7b7ef10ab54850c72e72e7693567");
        System.out.println(jsonResult);

        JSONObject jsonObject = JSON.parseObject(jsonResult);
        Object j = jsonObject.get("newslist");
        System.out.println(j);

        JSONArray jsonArray = JSON.parseArray(j+"");
        Object n1 = jsonArray.get(0);
        System.out.println("n1: " + n1);

        JSONObject jsonObject1 = JSON.parseObject(n1+"");
        Object n2 = jsonObject1.get("desc");
        System.out.println("n2: " + n2);

        JSONObject jsonObject2 = JSON.parseObject(n2 + "");
        System.out.println(jsonObject2.get("currentConfirmedCount"));
//        String jj = (String) j;
//        System.out.println(jj);
//        JSONArray jsonArray = JSON.parseArray(jj);
//        System.out.println(jsonArray.get());



//        JSONArray jsonArray = jsonObject.parseArray(jsonResult);

//        Object jsonArray = jsonObject.get("newslist");


//        System.out.println(j);
    }

    /**
     * @param httpUrl
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

package com.hack.infect_support.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hack.infect_support.dto.Country;
import com.hack.infect_support.dto.Province;
import com.hack.infect_support.dto.ProvinceCut;
import com.hack.infect_support.service.InfectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

@Api(tags = "InfectMainController" , description = "完成基础功能两个页面的接口")
@RestController
public class InfectMainController {

    @Autowired
    private InfectService infectService;

    @ApiOperation(value = "test")
    @GetMapping("/t")
    public String t(){

//        Country country = new Country();
//        country.setConfirmedCount(111);
//        String json = JSON.toJSONString(country);


//        List<ProvinceCut> provinceCuts = new LinkedList<>();
//
//        ProvinceCut provinceCut1 = new ProvinceCut();
//        provinceCut1.setConfirmedCount(111);
//        provinceCut1.setName("湖北");
//        provinceCuts.add(provinceCut1);
//
//        ProvinceCut provinceCut2 = new ProvinceCut();
//        provinceCut2.setName("福建");
//        provinceCut2.setConfirmedCount(222);
//        provinceCuts.add(provinceCut2);

        Province province = new Province();
        province.setCuredCount(1);
        province.setName("s");

        String json = JSON.toJSONString(province);
        return json;
    }

    @ApiOperation(value = "发送国家的具体信息")
    @GetMapping("/current")
    public String current(@RequestParam("date")String date){
        String info = infectService.getCountry(date);
        return info;
    }

    @ApiOperation(value = "发送各个省市当前的确证人数")
    @GetMapping("/provices")
    public String provinces(@RequestParam("date")String date){
        String info = infectService.getAllProvince(date);
        return info;
    }

    @ApiOperation(value = "发送当前省市的具体消息")
    @PostMapping("/specailProvince")
    public String specialProvince(@RequestBody String info){
        String infod = infectService.getSpecialProvince(info);
        return infod;
    }

    @ApiOperation(value = "发送地级市具体消息")
    @PostMapping("/getTown")
    public String getTown(@RequestBody String info){
        String infoo = infectService.getCities(info);
        return infoo;
    }

    @ApiOperation(value = "发送确证人数从以前到现在的全部数据")
    @GetMapping("/provincesConfirmed")
    public String provincesConfirmed(@RequestParam("name")String name){
        return null;
    }

    @ApiOperation(value = "发送疑似人数从以前到现在的全部数据")
    @GetMapping("/provincesSuspected")
    public String provincesSuspected(@RequestParam("name")String name){
        return null;
    }

    @ApiOperation(value = "发送治愈人数从以前到现在的全部数据")
    @GetMapping("/provincesCured")
    public String provincesCured(@RequestParam("name")String name){
        return null;
    }

    @ApiOperation(value = "发送死亡人数从以前到现在的全部数据")
    @GetMapping("/provincesDead")
    public String provincesDead(@RequestParam("name")String name){
        return null;
    }

}

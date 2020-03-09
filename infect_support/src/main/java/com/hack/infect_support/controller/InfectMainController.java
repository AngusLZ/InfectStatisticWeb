package com.hack.infect_support.controller;

import com.alibaba.fastjson.JSON;
import com.hack.infect_support.dto.Country;
import com.hack.infect_support.dto.Province;
import com.hack.infect_support.dto.ProvinceCut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "发送国家的具体信息" , httpMethod = "Get")
    @GetMapping("/current")
    public String current(){
        return null;
    }

    @ApiOperation(value = "发送各个省市当前的确证人数" , httpMethod = "Get")
    @GetMapping("/provices")
    public String provinces(){
        return null;
    }

    @ApiOperation(value = "发送当前省市的具体消息" , httpMethod = "Get")
    @GetMapping("/provincesCurrent")
    public String provincesCurrent(@RequestParam("name")String name){
        return null;
    }


    @ApiOperation(value = "发送确证人数从以前到现在的全部数据" , httpMethod = "Get")
    @GetMapping("/provincesConfirmed")
    public String provincesConfirmed(@RequestParam("name")String name){
        return null;
    }

    @ApiOperation(value = "发送疑似人数从以前到现在的全部数据" , httpMethod = "Get")
    @GetMapping("/provincesSuspected")
    public String provincesSuspected(@RequestParam("name")String name){
        return null;
    }
    @ApiOperation(value = "发送治愈人数从以前到现在的全部数据" , httpMethod = "Get")
    @GetMapping("/provincesCured")
    public String provincesCured(@RequestParam("name")String name){
        return null;
    }

    @ApiOperation(value = "发送死亡人数从以前到现在的全部数据" , httpMethod = "Get")
    @GetMapping("/provincesDead")
    public String provincesDead(@RequestParam("name")String name){
        return null;
    }

}

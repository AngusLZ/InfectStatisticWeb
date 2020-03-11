package com.hack.infect_support.controller;

import com.alibaba.fastjson.JSON;
import com.hack.infect_support.domain.Province;
import com.hack.infect_support.service.InfectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

@Api(tags = "InfectMainController" , description = "完成基础功能两个页面的接口")
@RestController
@CrossOrigin
public class InfectMainController {

    @Autowired
    private InfectService infectService;

    @ApiOperation(value = "配置启动")
    @GetMapping("/start")
    public void start(){
        infectService.setDb();
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

    @ApiOperation(value = "发送各个省市的累计确证人数")
    @GetMapping("/provincesConfirmedCount")
    public String provincesConfirmedCount(@RequestParam("date")String date){
        String info = infectService.getProvinceConfirmed(date);
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

    @ApiOperation(value = "选择不同图标所需要的数据")
    @PostMapping("/imgInfo")
    public String ImgInfo(@RequestBody String info){
        String infoo = infectService.ImgInfo(info);
        return infoo;
    }

}

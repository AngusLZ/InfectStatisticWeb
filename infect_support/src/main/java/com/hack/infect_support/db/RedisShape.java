package com.hack.infect_support.db;

import com.alibaba.fastjson.JSON;
import com.hack.infect_support.common.utils.Complete;
import com.hack.infect_support.dto.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 会飞的大野鸡
 * @create 2020/3/9
 * TODO:
 */

@Component
public class RedisShape {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setCountry(){
        Country country = new Country();
        country = new Complete().completeCountry(country);
        System.out.println(country);
        String countryInfo = JSON.toJSONString(country);
        System.out.println(countryInfo);



        System.out.println(stringRedisTemplate.opsForValue().get("k"));
        String key = "country";
        stringRedisTemplate.opsForValue().set(key , countryInfo);
    }


}

package com.hangeulbot.controller;

import com.hangeulbot.vo.HangeulbotDevice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyson on 2016. 7. 6..
 */
@RestController
public class APIController {

    @RequestMapping(value = "get/userInfo/{deviceId}",method = RequestMethod.GET)
    public HangeulbotDevice getUserInfoByDeviceId(@PathVariable String deviceId){
        /*HangeulbotDevice hangeulbotDevice1 = new HangeulbotDevice();
        hangeulbotDevice1.setDeviceId(hangeulbotDevice.getDeviceId());
        hangeulbotDevice1.setDeviceName(hangeulbotDevice.getDeviceName());*/
        //hangeulbotDevice1.setUserId(hangeulbotDevice.getUserId());
        HangeulbotDevice hangeulbotDevice = new HangeulbotDevice();
        hangeulbotDevice.setDeviceId(deviceId);
        System.out.println(deviceId);
        return hangeulbotDevice;
    }

}



package com.hangeulbot.controller;

import com.hangeulbot.service.APIService;
import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jyson on 2016. 7. 6..
 */
@RestController
public class APIController {

    @Autowired
    private APIService apiService;

    @RequestMapping(value = "get/userInfo",method = RequestMethod.POST)
    public ResponseEntity<HangeulbotDevice> getUserInfoByDeviceId(@RequestBody HangeulbotDevice hangeulbotDevice){
        /*HangeulbotDevice hangeulbotDevice1 = new HangeulbotDevice();
        hangeulbotDevice1.setDeviceId(hangeulbotDevice.getDeviceId());
        hangeulbotDevice1.setDeviceName(hangeulbotDevice.getDeviceName());*/
        //hangeulbotDevice1.setUserId(hangeulbotDevice.getUserId());
        System.out.println("Hangeulbot Device Info " + hangeulbotDevice.getDeviceId());

        return new ResponseEntity<HangeulbotDevice>(hangeulbotDevice, HttpStatus.OK);
    }


    @RequestMapping(value="get/isDuplicated/{userId}",method = RequestMethod.GET)
    public boolean isDuplicatedByEmailId(@PathVariable String userId){

        System.out.println("현재 API Server 로 넘어오는 이메일 값"+ userId);

        boolean flag=false;
        if(apiService.isDuplicatedByEmailId(userId)){
            flag=true;
        }
        return flag;
    }

    @RequestMapping(value = "save/userInfoAndDeviceInfo",method = RequestMethod.POST)
    public boolean userInfoAndDeviceInfo(@RequestBody HangeulbotUser hangeulbotUser){
        System.out.println("Hangeulbot Device Info " + hangeulbotUser.getHangeulbotDevices());
        return false;
    }


}



package com.hangeulbot.controller;

import com.hangeulbot.service.APIService;
import com.hangeulbot.vo.HangeulbotDevice;
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
    @RequestMapping(value="get/isDuplicated/{emailId}",method = RequestMethod.GET)
    public boolean isDuplicatedByEmailId(@PathVariable String memberId){
        boolean flag=false;
        if(apiService.isDuplicatedByEmailId(memberId)){
            flag=true;
        }
        return flag;
    }


}



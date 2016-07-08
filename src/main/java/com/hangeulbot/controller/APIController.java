package com.hangeulbot.controller;

import com.hangeulbot.service.APIService;
import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by jyson on 2016. 7. 6..
 */
@RestController
public class APIController {

    @Qualifier("APIServiceImpl")
    @Autowired
    private APIService apiService;

    @RequestMapping(value = "userInfo",method = RequestMethod.POST)
    public ResponseEntity<HangeulbotDevice> getUserInfoByDeviceId(@RequestBody HangeulbotDevice hangeulbotDevice){
        System.out.println("Hangeulbot Device Info " + hangeulbotDevice.getDeviceId());
        return new ResponseEntity<HangeulbotDevice>(hangeulbotDevice, HttpStatus.OK);
    }


    @RequestMapping(value="isDuplicated/",method = RequestMethod.POST)
    public boolean isDuplicatedByEmailId(@RequestBody String userId){

        System.out.println("현재 API Server 로 넘어오는 이메일 값"+ userId);

        boolean flag=false;
        if(apiService.isDuplicatedByEmailId(userId)){
            System.out.println("중복되는 아이디가 있네");
            flag=true;
        }
        return flag;
    }

    @RequestMapping(value = "deviceInfoAndUserInfo",method = RequestMethod.PUT)
    public ResponseEntity<HashMap<String,Object>> putDeviceInfoAndUserInfo(@RequestBody HangeulbotDevice hangeulbotDevice){
        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        HangeulbotUser hangeulbotUser = apiService.putHangeulbotDeviceInfoAndHangeulbotUserInfo(hangeulbotDevice);
        hangeulbotUser.setPassword("none");
        resultMap.put("token",Jwts.builder().setSubject(hangeulbotDevice.getHangeulbotUser().getUserId()).claim("roles", "parent").
                setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
        resultMap.put("hangeulbotUser",hangeulbotUser);
        return new ResponseEntity<HashMap<String,Object>>(resultMap,HttpStatus.OK);
    }


}



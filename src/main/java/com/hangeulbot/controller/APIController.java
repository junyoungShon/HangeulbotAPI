package com.hangeulbot.controller;

import com.hangeulbot.service.APIService;
import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
import java.util.HashMap;

/**
 * Created by jyson on 2016. 7. 6..
 */

@RestController
public class APIController {

    @Qualifier("APIServiceImpl")
    @Autowired
    private APIService apiService;




    @RequestMapping(value = "userInfoByDeviceId/{hangeulbotDeviceId}",method = RequestMethod.GET)
    public ResponseEntity<HashMap<String,Object>> getUserInfoByDeviceId(@PathVariable String hangeulbotDeviceId){
        System.out.println("Hangeulbot Device Info " + hangeulbotDeviceId);
        HashMap<String,Object> resultMap = apiService.getUserInfoByDeviceId(hangeulbotDeviceId);

        return new ResponseEntity<HashMap<String,Object>>(resultMap, HttpStatus.OK);
    }
    @RequestMapping(value = "deviceInfoAndUserInfo",method = RequestMethod.PUT)
    public ResponseEntity<HashMap<String,Object>> putDeviceInfoAndUserInfo(@RequestBody HangeulbotDevice hangeulbotDevice){
        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        System.out.println(hangeulbotDevice);
        HangeulbotUser hangeulbotUser = apiService.putHangeulbotDeviceInfoAndHangeulbotUserInfo(hangeulbotDevice);
        hangeulbotUser.setPassword("none");
        resultMap.put("token",Jwts.builder().setSubject(hangeulbotDevice.getHangeulbotUser().getUserId()).claim("roles", "parent").
                setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
        resultMap.put("hangeulbotUser",hangeulbotUser);
        return new ResponseEntity<HashMap<String,Object>>(resultMap,HttpStatus.OK);
    }

    @RequestMapping(value = "childImage",method = RequestMethod.POST)
    public boolean getUserInfoByDeviceId(@RequestParam("file") MultipartFile file){
        System.out.println("여기오냐");
        boolean flag= true;
        String fileName = null;

        System.out.println("파일의 이름"+file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf("?")));
        fileName = file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf("?"));

        if (!file.isEmpty()) {
            try {
                //Files.copy(file.getInputStream(), Paths.get("/Users/jyson/IdeaProjects/HangeulbotAPI/src/main/resources/static/img/", fileName));
                System.out.println("file start");
                System.out.println("name :"  + file.getName());
                System.out.println("file Name : "+fileName);
                System.out.println("file size : " + file.getSize());
            } catch (RuntimeException e) {
                flag=false;
                e.printStackTrace();
                System.out.println("여기오냐 에러");
           }
        } else {
                flag=false;
            System.out.println("여기오냐 그냥 빈파일");
        }
        System.out.println("asdfsd");
        return flag;
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




}



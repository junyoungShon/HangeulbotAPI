package com.hangeulbot.service;

import com.hangeulbot.dao.HangeulbotDeviceRepository;
import com.hangeulbot.dao.HangeulbotUserRepository;
import com.hangeulbot.vo.HangeulbotChild;
import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by junyoung on 2016-07-07.
 */
@Service
public class APIServiceImpl implements APIService{

    @Autowired
    private HangeulbotUserRepository hangeulbotUserRepository;
    @Autowired
    private HangeulbotDeviceRepository hangeulbotDeviceRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public boolean isDuplicatedByEmailId(String userId) {
        boolean flag = false;
        if(hangeulbotUserRepository.findByUserId(userId)!=null){
            flag = true;
        }
        return flag;
    }


    @Override
    public HangeulbotUser putHangeulbotDeviceInfoAndHangeulbotUserInfo(HangeulbotDevice hangeulbotDevice) {
        HangeulbotUser hangeulbotUser = hangeulbotDevice.getHangeulbotUser();
        String encodedPassword = bCryptPasswordEncoder.encode(hangeulbotUser.getPassword());
        hangeulbotUser.setPassword(encodedPassword);
        //hangeulbotUser.setHangeulbotDevice(hangeulbotDevice);
        hangeulbotUser = hangeulbotUserRepository.save(hangeulbotUser);
        hangeulbotDeviceRepository.save(hangeulbotDevice);
        return hangeulbotUser;
    }

    @Override
    public HashMap<String,Object> getUserInfoByDeviceId(String hangeulbotDeviceId) {

        HangeulbotDevice hangeulbotDevice = hangeulbotDeviceRepository.findOne(hangeulbotDeviceId);

        HashMap<String,Object> resultMap = new HashMap<String,Object>();
        System.out.println("hangeulbotDevice"+hangeulbotDevice);
        if(hangeulbotDevice!=null){
            if(hangeulbotDevice.getHangeulbotUser()!=null){
                System.out.println("성공");
                resultMap.put("flag","success");
                HangeulbotUser hangeulbotUser = hangeulbotDevice.getHangeulbotUser();
                hangeulbotUser.setPassword("none");
                resultMap.put("hangeulbotUser",hangeulbotUser);
                resultMap.put("token", Jwts.builder().setSubject(hangeulbotUser.getUserId()).claim("roles", "parent").
                        setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
            }else{
                System.out.println("실패");
                resultMap.put("flag","fail");

            }
        }else{
            resultMap.put("flag","fail");
        }
        return resultMap;
    }

}

package com.hangeulbot.service;

import com.hangeulbot.dao.HangeulbotDeviceRepository;
import com.hangeulbot.dao.HangeulbotUserRepository;
import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
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
        hangeulbotUser = hangeulbotUserRepository.save(hangeulbotUser);
        hangeulbotDeviceRepository.save(hangeulbotDevice);
        return hangeulbotUser;
    }


}

package com.hangeulbot.service;

import com.hangeulbot.dao.HangeulbotUserRepository;
import com.hangeulbot.vo.HangeulbotUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Created by junyoung on 2016-07-07.
 */
@Service
public class APIServiceImpl implements APIService{

    @Autowired
    private HangeulbotUserRepository hangeulbotUserRepository;

    @Override
    public boolean isDuplicatedByEmailId(String userId) {
        boolean flag = false;
        if(hangeulbotUserRepository.findByUserId(userId)!=null){
            flag = true;
        }
        return flag;
    }


    @Override
    public HangeulbotUser saveUserInfoAndDeviceInfo(HangeulbotUser hangeulbotUser) {

        hangeulbotUserRepository.save(hangeulbotUser).getUserId();
        return hangeulbotUser;
    }


}

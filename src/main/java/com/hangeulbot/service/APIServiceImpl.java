package com.hangeulbot.service;

import com.hangeulbot.dao.HangeulbotUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by junyoung on 2016-07-07.
 */
@Service
public class APIServiceImpl implements APIService{

    @Autowired
    private HangeulbotUserRepository hangeulbotUserRepository;

    @Override
    public boolean isDuplicatedByEmailId(String memberId) {
        boolean flag = false;
        if(hangeulbotUserRepository.findByUserId(memberId)!=null){
            flag = true;
        }
        return flag;
    }

    public boolean isDuplicatedByIndex(int i) {
        boolean flag  = false;
        System.out.println("여기왜 안오냐"+hangeulbotUserRepository);
        if(hangeulbotUserRepository.findOne(i)!=null){
            flag= true;
        }
        return flag;
    }
}

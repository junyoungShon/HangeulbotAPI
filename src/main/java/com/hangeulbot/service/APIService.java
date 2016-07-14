package com.hangeulbot.service;

import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by jyson on 2016. 7. 6..
 */
@Service
public interface APIService {
    boolean isDuplicatedByEmailId(String userId);

    HangeulbotUser putHangeulbotDeviceInfoAndHangeulbotUserInfo(HangeulbotDevice hangeulbotDevice);

    HashMap<String,Object> getUserInfoByDeviceId(String hangeulbotDeviceId);
}

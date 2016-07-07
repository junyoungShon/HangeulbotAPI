package com.hangeulbot;

import com.hangeulbot.service.APIService;
import com.hangeulbot.service.APIServiceImpl;
import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HangeulbotApiApplication.class)
@WebAppConfiguration
public class HangeulbotApiApplicationTests {

	@Autowired
	private APIService apiService;

	@Test
	public void contextLoads() {
		try {
			HangeulbotUser hangeulbotUser = new HangeulbotUser();
			HangeulbotDevice hangeulbotDevice = new HangeulbotDevice();
			hangeulbotUser.setUserId("imvestt@hanmail.net");
			hangeulbotUser.setPassword("1234");
			hangeulbotUser.setPhoneNumber("01026789441");
			hangeulbotDevice.setDeviceId("22:22:22:22");
			hangeulbotDevice.setDeviceName("똘기떵이의 한글봇");
			hangeulbotDevice.setHangeulbotUser(hangeulbotUser);
			ArrayList<HangeulbotDevice> list = new ArrayList<HangeulbotDevice>();
			list.add(hangeulbotDevice);
			hangeulbotUser.setHangeulbotDevices(list);

			System.out.println("세이브 메서드 결과 " + apiService.saveUserInfoAndDeviceInfo(hangeulbotUser));

			System.out.println("테스트 결과"+apiService.isDuplicatedByEmailId("imvestt@hanmail.net"));
			//apiService.isDuplicatedByIndex("imvestt");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

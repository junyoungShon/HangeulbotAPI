package com.hangeulbot;

import com.hangeulbot.service.APIService;
import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HangeulbotApiApplication.class)
@WebAppConfiguration
public class HangeulbotApiApplicationTests {


	@Qualifier("APIServiceImpl")
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


			hangeulbotUser.setHangeulbotDevice(hangeulbotDevice);

			System.out.println("세이브 메서드 결과 " + apiService.putHangeulbotDeviceInfoAndHangeulbotUserInfo(hangeulbotDevice));

			System.out.println("테스트 결과"+apiService.isDuplicatedByEmailId("imvestt@hanmail.net"));


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

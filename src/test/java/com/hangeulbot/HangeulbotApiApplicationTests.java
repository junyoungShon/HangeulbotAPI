package com.hangeulbot;

import com.hangeulbot.service.APIService;
import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HangeulbotApiApplication.class)
@WebAppConfiguration
public class HangeulbotApiApplicationTests {


	@Qualifier("APIServiceImpl")
	@Autowired
	private APIService apiService;


	private HttpServlet httpServlet;

	@Before
	public void setUp(){
		httpServlet = new HttpServlet() {
		};
	}

	@Test
	public void contextLoads() throws IOException {
		System.out.println("여긴오냐");
		/*try {
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
		}*/
		HangeulbotDevice hangeulbotDevice = new HangeulbotDevice();
		hangeulbotDevice.setDeviceId("22:22:22:22");
		//apiService.getUserInfoByDeviceId(hangeulbotDevice);
	}


}

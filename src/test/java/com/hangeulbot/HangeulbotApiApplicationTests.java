package com.hangeulbot;

import com.hangeulbot.service.APIService;
import com.hangeulbot.service.APIServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HangeulbotApiApplication.class)
@WebAppConfiguration
public class HangeulbotApiApplicationTests {

	@Test
	public void contextLoads() {
		try {

			APIServiceImpl apiService = new APIServiceImpl();
			//System.out.println(apiService.isDuplicatedByEmailId("imvestt"));
			apiService.isDuplicatedByIndex(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

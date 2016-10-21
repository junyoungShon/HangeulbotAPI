package com.hangeulbot;

import com.hangeulbot.service.APIService;
import com.hangeulbot.vo.HangeulbotDevice;
import com.hangeulbot.vo.HangeulbotUser;
import com.hangeulbot.vo.HangeulbotUserWordLog;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HangeulbotApiApplication.class)
@WebAppConfiguration
public class HangeulbotApiApplicationTests {

	// Define the logger object for this class
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Qualifier("APIServiceImpl")
	@Autowired
	private APIService apiService;

	@Test
	public void contextLoads() throws IOException {
		System.out.println("여긴오냐");
		apiService.getWordListForDefaultWordGame("imvestt@hanmail.net_0","");
	}


}

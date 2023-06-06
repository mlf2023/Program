package com.ztt.mt;

import com.aliyuncs.exceptions.ClientException;
import com.ztt.mt.common.SmsProperties;
import com.ztt.mt.email.SendEmailUtils;
import com.ztt.mt.email.ValidateCodeUtils;
import com.ztt.mt.utils.SmsUtils;
import org.apache.commons.mail.EmailException;
import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MtTakeOutApplicationTests {
@Autowired
private SmsUtils smsUtils;
@Autowired
private SmsProperties pro;
	@Test
	void contextLoads() throws ClientException, InterruptedException {
		for (;;) {
			Thread.sleep(60000);
			smsUtils.sendSms("18281338184","123456",pro.getSignName(),pro.getVerifyCodeTemplate());
		}
	}
	@Test
	public void email() throws InterruptedException, EmailException {
		do {
			SendEmailUtils.sendAuthCodeEmail("1033468588@qq.com","别卷了");
			Thread.sleep(1000);
		} while (true);
	}
	@Test
	public void t() throws EmailException {
		SendEmailUtils.sendAuthCodeEmail("484283240@qq.com","别卷了");
	}

}

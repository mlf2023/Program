package com.ztt.mt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class MtTakeOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtTakeOutApplication.class, args);
	}

}

package com.thetrustlesstrio.TrustSurveyServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 나중에 Database 쓰기 위해서는 얘 하기 넣어야 함
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TrustSurveyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrustSurveyServerApplication.class, args);
	}

}

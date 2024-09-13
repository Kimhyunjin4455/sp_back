package starbucks3355.starbucksServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import starbucks3355.starbucksServer.common.config.KakaoProperties;

@SpringBootApplication
@EnableConfigurationProperties(KakaoProperties.class)
@EnableJpaAuditing
public class StarbucksServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarbucksServerApplication.class, args);
	}

}

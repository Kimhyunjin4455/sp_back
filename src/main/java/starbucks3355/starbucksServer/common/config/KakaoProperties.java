package starbucks3355.starbucksServer.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "kakaopay")
@Getter
@Setter
@NoArgsConstructor
public class KakaoProperties {
	private String adminKey;
	private String cid;
	private String readyUrl;
	private String approveUrl;

}

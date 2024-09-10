package starbucks3355.starbucksServer.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix = "kakaopay")
@Getter
@NoArgsConstructor
public class KakaoProperties {
	private String adminKey;
	private String cid;
	private String readyUrl;
	private String approveUrl;

	@Builder
	public KakaoProperties(String adminKey, String cid, String readyUrl, String approveUrl) {
		this.adminKey = adminKey;
		this.cid = cid;
		this.readyUrl = readyUrl;
		this.approveUrl = approveUrl;
	}

}

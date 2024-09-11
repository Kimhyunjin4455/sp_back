package starbucks3355.starbucksServer.domainOrders.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString

public class KakaoResponseReadyDto {
	private String tid;
	@JsonProperty("next_redirect_pc_url")
	private String nextRedirectPcUrl;

	@Builder
	public KakaoResponseReadyDto(String tid, String nextRedirectPcUrl) {
		this.tid = tid;
		this.nextRedirectPcUrl = nextRedirectPcUrl;

	}
}

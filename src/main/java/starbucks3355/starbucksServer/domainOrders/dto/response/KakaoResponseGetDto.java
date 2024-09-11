package starbucks3355.starbucksServer.domainOrders.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString

public class KakaoResponseGetDto {
	private String tid;
	private String nextRedirectPcUrl;

	@Builder
	public KakaoResponseGetDto(String tid, String nextRedirectPcUrl) {
		this.tid = tid;
		this.nextRedirectPcUrl = nextRedirectPcUrl;
	}
}

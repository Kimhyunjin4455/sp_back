package starbucks3355.starbucksServer.domainOrders.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class KakaoRequestApproveDto {
	private String cid;
	private String tid;
	private String partnerOrderId;
	private String partnerUserId;
	private String pgToken;

	@Builder
	public KakaoRequestApproveDto(String cid, String tid, String partnerOrderId, String partnerUserId, String pgToken) {
		this.cid = cid;
		this.tid = tid;
		this.partnerOrderId = partnerOrderId;
		this.partnerUserId = partnerUserId;
		this.pgToken = pgToken;
	}
}

package starbucks3355.starbucksServer.domainOrders.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.domainOrders.entity.Amount;

@Getter
@NoArgsConstructor
@ToString
public class KakaoResponseApproveDto {
	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partnerOrderId;
	private String partnerUserId;
	private String paymentMethodType;
	private Amount amount;

	@Builder
	public KakaoResponseApproveDto(String aid, String tid, String cid, String sid, String partnerOrderId,
		String partnerUserId, String paymentMethodType, Amount amount) {
		this.aid = aid;
		this.tid = tid;
		this.cid = cid;
		this.sid = sid;
		this.partnerOrderId = partnerOrderId;
		this.partnerUserId = partnerUserId;
		this.paymentMethodType = paymentMethodType;
		this.amount = amount;
	}
}

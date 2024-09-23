package starbucks3355.starbucksServer.domainOrders.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class KakaoRequestApproveDto {
	// 결제 승인 요청 값
	private String cid;
	private String tid;
	private String partnerOrderId;
	private String partnerUserId;
	private String pgToken;
	private String productName;
	private Integer productQuantity;
	private Integer totalAmount;

	@Builder
	public KakaoRequestApproveDto(String tid, String partnerOrderId, String partnerUserId,
		String pgToken, String cid, String productName, Integer productQuantity, Integer totalAmount) {
		this.cid = cid;
		this.tid = tid;
		this.partnerOrderId = partnerOrderId;
		this.partnerUserId = partnerUserId;
		this.pgToken = pgToken;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.totalAmount = totalAmount;
	}
}

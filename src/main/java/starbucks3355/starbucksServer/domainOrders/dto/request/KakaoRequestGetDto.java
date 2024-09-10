package starbucks3355.starbucksServer.domainOrders.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class KakaoRequestGetDto {

	private String cid;
	private String partnerOrderId;
	private String itemName;
	private Integer quantity;
	private Integer totalAmount;
	private Integer taxFreeAmount;
	private String approvalUrl;
	private String cancelUrl;
	private String failUrl;
	private String tid;
	private String pgToken;

	@Builder
	public KakaoRequestGetDto(String cid, String partnerOrderId, String itemName, Integer quantity, Integer totalAmount,
		Integer taxFreeAmount, String approvalUrl, String cancelUrl, String failUrl, String tid, String pgToken) {
		this.cid = cid;
		this.partnerOrderId = partnerOrderId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.taxFreeAmount = taxFreeAmount;
		this.approvalUrl = approvalUrl;
		this.cancelUrl = cancelUrl;
		this.failUrl = failUrl;
		this.tid = tid;
		this.pgToken = pgToken;
	}
}

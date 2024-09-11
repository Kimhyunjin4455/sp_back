package starbucks3355.starbucksServer.domainOrders.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class KakaoRequestReadyDto {
	private String cid;
	private String partnerOrderId;
	private String partnerUserId;
	private String itemName;
	private Integer quantity;
	private Integer totalAmount;
	private Integer vatAmount;
	private Integer taxFreeAmount;
	private String approvalUrl;
	private String failUrl;
	private String cancelUrl;

	@Builder
	public KakaoRequestReadyDto(String partnerOrderId, String itemName, Integer quantity,
		Integer totalAmount,
		Integer taxFreeAmount, String approvalUrl, String cancelUrl, String failUrl, String partnerUserId,
		Integer vatAmount, String cid) {
		this.partnerOrderId = partnerOrderId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.taxFreeAmount = taxFreeAmount;
		this.approvalUrl = approvalUrl;
		this.cancelUrl = cancelUrl;
		this.failUrl = failUrl;
		this.partnerUserId = partnerUserId;
		this.vatAmount = vatAmount;
		this.cid = cid;
	}
}

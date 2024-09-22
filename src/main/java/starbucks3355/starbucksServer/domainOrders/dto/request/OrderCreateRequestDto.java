package starbucks3355.starbucksServer.domainOrders.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequestDto {
	private String cid; // 가맹점 코드
	private String partnerOrderId; // 주문번호
	private String partnerUserId; // 회원번호
	private String productName; // 상품명
	private Integer quantity; // 수량
	private Integer totalAmount; // 총액
	private Integer taxFreeAmount; // 비과세 금액
	private String approvalUrl;     // 결제 성공 시 Redirect URL
	private String cancelUrl;       // 결제 취소 시 Redirect URL
	private String failUrl;         // 결제 실패 시 Redirect URL

	@Builder
	public OrderCreateRequestDto(String cid, String partnerOrderId, String partnerUserId, String productName,
		Integer quantity, Integer totalAmount, Integer taxFreeAmount, String approvalUrl, String cancelUrl,
		String failUrl) {
		this.cid = cid;
		this.partnerOrderId = partnerOrderId;
		this.partnerUserId = partnerUserId;
		this.productName = productName;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.taxFreeAmount = taxFreeAmount;
		this.approvalUrl = approvalUrl;
		this.cancelUrl = cancelUrl;
		this.failUrl = failUrl;
	}

	// Dto를 Order 엔티티로 변환 toEntity 메서드
}

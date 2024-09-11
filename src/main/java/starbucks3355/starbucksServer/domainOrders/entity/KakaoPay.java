package starbucks3355.starbucksServer.domainOrders.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class KakaoPay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kakao_id")
	private Long id;
	@Column(nullable = false, length = 10)
	private String cid; // 가맹점 코드, 10자
	@Column(nullable = false, length = 100)
	private String partnerOrderId; // 가맹점 주문번호, 최대 100자
	@Column(nullable = false, length = 100)
	private String partnerUserId; // 가맹점 회원 id, 최대 100자
	@Column(nullable = false, length = 100)
	private String itemName; // 상품명, 최대 100자
	@Column(nullable = false)
	private Integer quantity; // 상품 수량
	@Column(nullable = false)
	private Integer totalAmount; // 상품 총액
	@Column(nullable = false)
	private Integer taxFreeAmount; // 상품 비과세 금액
	@Column(nullable = false, length = 300)
	private String approvalUrl; // 결제 성공 시 Redirect URL. 최대 255자
	@Column(nullable = false, length = 300)
	private String cancelUrl; // 결제 취소 시 Redirect URL. 최대 255자
	@Column(nullable = false, length = 300)
	private String failUrl; // 결제 실패 시 Redirect URL. 최대 255자

	@Builder
	public KakaoPay(Long id, String cid, String partnerOrderId, String itemName, Integer quantity,
		Integer totalAmount,
		Integer taxFreeAmount, String approvalUrl, String cancelUrl, String failUrl, String partnerUserId) {
		this.id = id;
		this.cid = cid;
		this.partnerOrderId = partnerOrderId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.taxFreeAmount = taxFreeAmount;
		this.approvalUrl = approvalUrl;
		this.cancelUrl = cancelUrl;
		this.failUrl = failUrl;
		this.partnerUserId = partnerUserId;
	}
}

package starbucks3355.starbucksServer.domainOrders.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.domainOrders.entity.Amount;

@Getter
@NoArgsConstructor
@ToString
public class KakaoResponseApproveDto {
	// 응답 값
	private String aid;
	private String tid;
	private String cid;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type; //결제수단
	private Amount amount; // 결제 금액 정보
	private Integer tax_free_amount; // 비과세 금액
	private String item_name; // 상품 이름
	private Integer quantity; // 상품 수량
	private LocalDateTime created_at; // 결제 준비 요청 시각
	private LocalDateTime approved_at; // 결제 승인 시각
	private String payload; // 결제 승인 요청에 대해 저장한 값

	@Builder
	public KakaoResponseApproveDto(String aid, String tid, String cid, String partner_order_id,
		String partner_user_id, String payment_method_type, Amount amount, String item_name, Integer quantity
		, LocalDateTime created_at, LocalDateTime approved_at, String payload, Integer tax_free_amount) {
		this.aid = aid;
		this.tid = tid;
		this.cid = cid;
		this.partner_order_id = partner_order_id;
		this.partner_user_id = partner_user_id;
		this.payment_method_type = payment_method_type;
		this.amount = amount;
		this.item_name = item_name;
		this.quantity = quantity;
		this.created_at = created_at;
		this.approved_at = approved_at;
		this.payload = payload;
		this.tax_free_amount = tax_free_amount;
	}
}

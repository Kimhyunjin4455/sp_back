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
	// 응답 값
	private String aid;
	private String tid;
	private String cid;
	private String partner_order_id;
	private String partner_user_id;
	private String paymentMethodType;
	private Amount amount;
	private String item_name; // 상품 이름
	private Integer quantity; // 상품 수량

	@Builder
	public KakaoResponseApproveDto(String aid, String tid, String cid, String sid, String partner_order_id,
		String partner_user_id, String paymentMethodType, Amount amount, String item_name, Integer quantity) {
		this.aid = aid;
		this.tid = tid;
		this.cid = cid;
		this.partner_order_id = partner_order_id;
		this.partner_user_id = partner_user_id;
		this.paymentMethodType = paymentMethodType;
		this.amount = amount;
		this.item_name = item_name;
		this.quantity = quantity;
	}
}

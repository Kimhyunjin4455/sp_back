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
	private String partner_order_id;
	private String partner_user_id;
	private String pgToken;
	private String item_name;
	private Integer quantity;
	private Integer total_Amount;

	@Builder
	public KakaoRequestApproveDto(String cid, String tid, String partner_order_id, String partner_user_id,
		String pgToken,
		String item_name, Integer quantity, Integer total_Amount) {
		this.cid = cid;
		this.tid = tid;
		this.partner_order_id = partner_order_id;
		this.partner_user_id = partner_user_id;
		this.pgToken = pgToken;
		this.item_name = item_name;
		this.quantity = quantity;
		this.total_Amount = total_Amount;
	}

}

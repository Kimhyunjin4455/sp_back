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
	private String partner_order_id;
	private String partner_user_id;
	private String item_name;
	private Integer quantity;
	private Integer total_amount;
	private Integer vat_amount;
	private Integer tax_free_amount;
	private String approv_url;
	private String fail_url;
	private String cancel_url;

	@Builder
	public KakaoRequestReadyDto(String cid, String partner_order_id, String partner_user_id, String item_name,
		Integer quantity, Integer total_amount, Integer vat_amount, Integer tax_free_amount, String approv_url,
		String fail_url, String cancel_url) {
		this.cid = cid;
		this.partner_order_id = partner_order_id;
		this.partner_user_id = partner_user_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.total_amount = total_amount;
		this.vat_amount = vat_amount;
		this.tax_free_amount = tax_free_amount;
		this.approv_url = approv_url;
		this.fail_url = fail_url;
		this.cancel_url = cancel_url;
	}
}

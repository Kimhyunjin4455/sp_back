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
	private String partner_order_id; // 가맹점 주문번호, 최대 100자
	@Column(nullable = false, length = 100)
	private String partner_user_id; // 가맹점 회원 id, 최대 100자
	@Column(nullable = false, length = 100)
	private String item_Name; // 상품명, 최대 100자
	@Column(nullable = false)
	private Integer quantity; // 상품 수량
	@Column(nullable = false)
	private Integer total_amount; // 상품 총액
	@Column(nullable = false)
	private Integer tax_free_amount; // 상품 비과세 금액
	@Column(nullable = false, length = 300)
	private String approve_url; // 결제 성공 시 Redirect URL. 최대 255자
	@Column(nullable = false, length = 300)
	private String cancel_url; // 결제 취소 시 Redirect URL. 최대 255자
	@Column(nullable = false, length = 300)
	private String fail_url; // 결제 실패 시 Redirect URL. 최대 255자

	@Builder
	public KakaoPay(Long id, String cid, String partner_order_id, String partner_user_id, String item_Name,
		Integer quantity, Integer total_amount, Integer tax_free_amount, String approve_url, String cancel_url,
		String fail_url) {
		this.id = id;
		this.cid = cid;
		this.partner_order_id = partner_order_id;
		this.partner_user_id = partner_user_id;
		this.item_Name = item_Name;
		this.quantity = quantity;
		this.total_amount = total_amount;
		this.tax_free_amount = tax_free_amount;
		this.approve_url = approve_url;
		this.cancel_url = cancel_url;
		this.fail_url = fail_url;
	}

}

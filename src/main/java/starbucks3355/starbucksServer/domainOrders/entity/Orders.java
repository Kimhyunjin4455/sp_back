package starbucks3355.starbucksServer.domainOrders.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.common.entity.BaseEntity;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Orders extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 30)
	private Long id;
	@Column(nullable = false, length = 30)
	private String orderId; // 주문번호
	@Column(nullable = false, length = 30)
	private String userId; // 사용자 id
	@Column(nullable = false, length = 40)
	private Integer totalAmount; // 총 결제 금액
	@Column(nullable = false, length = 30)
	private String productName; // 상품명
	@Column(nullable = false, length = 30)
	private Integer productQuantity; // 상품 수량

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private PayStatus payStatus; // 결제 상태

	@Builder
	public Orders(Long id, String orderId, String userId, Integer totalAmount, String productName,
		Integer productQuantity,
		PayStatus payStatus) {
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.payStatus = payStatus;
	}
}

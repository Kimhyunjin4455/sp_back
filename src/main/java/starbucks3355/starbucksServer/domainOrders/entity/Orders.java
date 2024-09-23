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
	@Column(nullable = false, length = 100)
	private String orderId; // 주문번호
	@Column(nullable = false, length = 30)
	private String userId; // 사용자 id
	@Column(nullable = false, length = 40)
	private Integer totalAmount; // 총 결제 금액
	@Column(nullable = false, length = 30)
	private String productUuid; // 상품 uuid
	@Column(nullable = false, length = 30)
	private Integer productQuantity; // 상품 수량
	@Column(nullable = false, length = 10)
	private OrderStatus orderStatus; // 주문 상태
	@Column(nullable = false, length = 100)
	private String address; // 주소
	@Column(nullable = false, length = 100)
	private String detailAddress; // 상세 주소
	@Column(nullable = false, length = 20)
	private String phone1; // 전화번호
	@Column(nullable = false, length = 20)
	private String userName; // 배송에서 이름 다르게 쓸 수도 있으니

	// @Enumerated(EnumType.STRING)
	// @Column(nullable = false, length = 10)
	// private PayStatus payStatus; // 결제 상태

	@Builder
	public Orders(Long id, String orderId, String userId, Integer totalAmount, String productUuid,
		Integer productQuantity, OrderStatus orderStatus,
		String address, String detailAddress, String phone1, String userName) {
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.productUuid = productUuid;
		this.productQuantity = productQuantity;
		this.orderStatus = orderStatus;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phone1 = phone1;
		this.userName = userName;
	}
}




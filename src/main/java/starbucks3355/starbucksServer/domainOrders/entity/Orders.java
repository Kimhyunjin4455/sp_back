package starbucks3355.starbucksServer.domainOrders.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 30)
	private Long id;
	@Column(nullable = false, length = 30)
	private LocalDateTime orderDate;
	@Column(nullable = false, length = 40)
	private Integer totalAmount;
	@Column(nullable = false, length = 100)
	private UUID uuid;
	@Column(nullable = false, length = 30)
	private String userName;
	@Column(nullable = false, length = 30)
	private String userPhoneNumber;
	@Column(nullable = false, length = 30)
	private String userAddress;

	@Enumerated
	@Column(nullable = false, length = 10)
	private OrderStatus orderStatus;

	@Builder
	public Orders(Long id, LocalDateTime orderDate, Integer totalAmount, UUID uuid, String userName,
		String userPhoneNumber, String userAddress, OrderStatus orderStatus) {
		this.id = id;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.uuid = uuid;
		this.userName = userName;
		this.userPhoneNumber = userPhoneNumber;
		this.userAddress = userAddress;
		this.orderStatus = orderStatus;
	}

	public void updateOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}

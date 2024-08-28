package starbucks3355.starbucksServer.domainOrders.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class OrderPayDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 30)
	private Long id;
	@OneToOne
	@JoinColumn(name = "payment_id", nullable = false)
	private OrderPay paymentId;
	@Column(nullable = true, length = 30)
	private Long orderId;
	@Column(nullable = true, length = 10)
	private String paymentStatus;
}

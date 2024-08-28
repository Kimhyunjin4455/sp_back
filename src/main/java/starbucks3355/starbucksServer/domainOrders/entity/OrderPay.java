package starbucks3355.starbucksServer.domainOrders.entity;

import java.time.LocalDateTime;

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
public class OrderPay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 30)
	private Long id;
	@OneToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order orderId;
	@Column(nullable = false, length = 30)
	private LocalDateTime paymentDate;
	@Column(nullable = false, length = 20)
	private Integer paymentAmount;
	@Column(nullable = false, length = 20)
	private String paymentMethod;
	@Column(nullable = false, length = 10)
	private boolean isPaymentStatus;

}

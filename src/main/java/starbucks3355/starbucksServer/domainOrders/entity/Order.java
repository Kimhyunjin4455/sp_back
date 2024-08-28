package starbucks3355.starbucksServer.domainOrders.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 30)
	private Long id;
	@Column(nullable = false, length = 30)
	private LocalDateTime orderDate;
	@Column(nullable = false, length = 40)
	private Integer totalAmount;
	@Column(nullable = false, length = 100)
	private UUID uuId;
	@Column(nullable = false, length = 30)
	private String userName;
	@Column(nullable = false, length = 30)
	private String userPhoneNumber;
	@Column(nullable = false, length = 30)
	private String userAddress;

}

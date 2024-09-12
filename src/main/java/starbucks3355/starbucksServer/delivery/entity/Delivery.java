package starbucks3355.starbucksServer.delivery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
public class Delivery {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long deliveryId;
	@Column(length = 15)
	private String nickname;
	@Column(nullable = false, length = 20)
	private Integer postNumber;
	@Column(nullable = false, length = 100)
	private String address;
	@Column(nullable = false, length = 100)
	private String detailAddress;
	@Column(nullable = false, length = 20)
	private Integer phone1;
	@Column(length = 20)
	private Integer phone2;
	@Column(length = 20)
	private String message;
	@Column(nullable = false)
	private boolean isBase;

}

package starbucks3355.starbucksServer.shipping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShippingMemberAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 30)
	private Long deliveryId;
	@Column(nullable = false, length = 255)
	private String address;
	@Column(nullable = false)
	private boolean basicAddress;
	@Column(length = 20)
	private String nickname;
	@Column(nullable = false, length = 30, unique = true)
	private String receiver;
	@Column(nullable = false, length = 30)
	private String phone1;
	@Column(length = 30)
	private String phone2;
	@Column(length = 30)
	private String message;
	@Column(nullable = false)
	private String uuid;
}

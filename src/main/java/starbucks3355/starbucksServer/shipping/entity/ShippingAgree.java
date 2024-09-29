package starbucks3355.starbucksServer.shipping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ShippingAgree {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;
	private String uuid;
	private boolean shippingAgree;

	@Builder
	public ShippingAgree(String uuid, boolean shippingAgree, Long id) {
		this.id = id;
		this.uuid = uuid;
		this.shippingAgree = shippingAgree;
	}
}

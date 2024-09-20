package starbucks3355.starbucksServer.vendor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ProductByPromotionList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String promotionUuid;
	private String productUuid;

	@Builder
	public ProductByPromotionList(String promotionUuid, String productUuid) {
		this.promotionUuid = promotionUuid;
		this.productUuid = productUuid;
	}

}

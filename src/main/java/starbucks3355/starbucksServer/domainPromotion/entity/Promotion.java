package starbucks3355.starbucksServer.domainPromotion.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 255)
	private String promotionName;
	private Boolean isState;
	@Column(columnDefinition = "TEXT")
	private String promotionInfoContent;
	private String promotionUuid;
	// private String productUuid;

	@Builder
	public Promotion(String promotionName, Boolean isState, String promotionInfoContent) {
		this.promotionName = promotionName;
		this.isState = isState;
		this.promotionInfoContent = promotionInfoContent;
		//this.productUuid = productUuid;
	}
}

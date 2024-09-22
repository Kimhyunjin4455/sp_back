package starbucks3355.starbucksServer.domainProduct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class ProductFlags {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private boolean isNew;
	@Column(nullable = false)
	private boolean isBest; // 전체 상품 중 10개 뽑기
	@Column(nullable = false)
	private boolean isLimited;
	@Column(nullable = false)
	private String productUuid;

	@Builder
	public ProductFlags(Long id, boolean isNew, boolean isBest, String productUuid) {
		this.id = id;
		this.isNew = isNew;
		this.isBest = isBest;
		this.productUuid = productUuid;
	}

	@Builder
	public ProductFlags(boolean isNew, boolean isBest, String productUuid) {
		this.isNew = isNew;
		this.isBest = isBest;
		this.productUuid = productUuid;
	}
}

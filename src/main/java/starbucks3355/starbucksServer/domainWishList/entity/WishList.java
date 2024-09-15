package starbucks3355.starbucksServer.domainWishList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.common.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class WishList extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private int limitQuantity;
	private String memberUuid;
	private boolean isChecked;
	@Column(length = 100)
	private String productUuid;
	private Integer currentQuantity;

	@Builder
	public WishList(int limitQuantity, String memberUuid, boolean isChecked, String productUuid,
		Integer currentQuantity) {
		this.limitQuantity = limitQuantity;
		this.memberUuid = memberUuid;
		this.isChecked = true;
		this.productUuid = productUuid;
		this.currentQuantity = currentQuantity;
	}

	public void updateCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public void updateChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
}

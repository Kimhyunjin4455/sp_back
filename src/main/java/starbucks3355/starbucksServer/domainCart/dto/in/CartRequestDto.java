package starbucks3355.starbucksServer.domainCart.dto.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainCart.entity.WishList;

@Getter
@Builder
public class CartRequestDto {
	private String productUuid;
	private String memberUuid;
	private boolean isChecked;
	private Integer limitQuantity;
	private Integer currentQuantity;
	private LocalDateTime regDate, modDate;

	public WishList toEntity(String productUuid, String memberUuid) {
		return WishList.builder()
			.productUuid(productUuid)
			.memberUuid(memberUuid)
			.isChecked(isChecked)
			.limitQuantity(limitQuantity)
			.currentQuantity(currentQuantity)
			.build();
	}

	public void updateCurrentQuantity(int quantity) {
		this.currentQuantity = quantity;
	}
}

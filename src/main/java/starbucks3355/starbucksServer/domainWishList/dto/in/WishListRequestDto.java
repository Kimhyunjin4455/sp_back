package starbucks3355.starbucksServer.domainWishList.dto.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainWishList.entity.WishList;

@Getter
@Builder
public class WishListRequestDto {
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

}

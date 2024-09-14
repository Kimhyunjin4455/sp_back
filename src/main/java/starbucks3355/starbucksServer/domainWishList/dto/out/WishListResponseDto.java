package starbucks3355.starbucksServer.domainWishList.dto.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainWishList.vo.out.WishListResponseVo;

@Getter
@Builder
public class WishListResponseDto {
	private String productUuid;
	private String memberUuid;
	private boolean isChecked;
	private Integer limitQuantity;
	private Integer currentQuantity;
	private LocalDateTime regDate, modDate;

	public WishListResponseVo dtoToResponseVo() {
		return WishListResponseVo.builder()
			.productUuid(productUuid)
			.memberUuid(memberUuid)
			.isChecked(isChecked)
			.limitQuantity(limitQuantity)
			.currentQuantity(currentQuantity)
			.regDate(regDate)
			.modDate(modDate)
			.build();
	}
}

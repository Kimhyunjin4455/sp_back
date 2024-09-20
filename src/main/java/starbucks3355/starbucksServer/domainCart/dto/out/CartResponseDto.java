package starbucks3355.starbucksServer.domainCart.dto.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainCart.vo.out.CartResponseVo;

@Getter
@Builder
public class CartResponseDto {
	private String productUuid;
	private String memberUuid;
	private boolean isChecked;
	private Integer limitQuantity;
	private Integer currentQuantity;
	private LocalDateTime regDate, modDate;

	public CartResponseVo dtoToResponseVo() {
		return CartResponseVo.builder()
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

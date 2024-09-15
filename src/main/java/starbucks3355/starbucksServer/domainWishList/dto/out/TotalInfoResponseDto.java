package starbucks3355.starbucksServer.domainWishList.dto.out;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainWishList.vo.out.TotalInfoResponseVo;

@Getter
@Builder
public class TotalInfoResponseDto {
	private Integer totalDiscount;
	private Integer totalPrice;

	public TotalInfoResponseVo dtoToResponseVo() {
		return TotalInfoResponseVo.builder()
			.totalDiscount(totalDiscount)
			.totalPrice(totalPrice)
			.build();
	}
}

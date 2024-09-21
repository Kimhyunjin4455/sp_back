package starbucks3355.starbucksServer.domainCart.dto.out;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainCart.vo.out.TotalInfoResponseVo;

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

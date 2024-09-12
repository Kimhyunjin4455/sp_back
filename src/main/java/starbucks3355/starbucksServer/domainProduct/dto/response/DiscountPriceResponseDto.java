package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.entity.DiscountType;
import starbucks3355.starbucksServer.domainProduct.vo.response.DiscountPriceResponseVo;

@Getter
@Builder
public class DiscountPriceResponseDto {
	private DiscountType discountType;
	private Integer discountPrice;

	public DiscountPriceResponseVo dtoToResponseVo() {
		return DiscountPriceResponseVo.builder()
			.discountType(discountType)
			.discountPrice(discountPrice)
			.build();
	}
}

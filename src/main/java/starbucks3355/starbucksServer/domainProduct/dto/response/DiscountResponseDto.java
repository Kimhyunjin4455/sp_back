package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.entity.DiscountType;
import starbucks3355.starbucksServer.domainProduct.vo.response.DiscountResponseVo;

@Getter
@Builder
public class DiscountResponseDto {
	private DiscountType discountType;
	private Integer discountValue;

	public DiscountResponseVo dtoToResponseVo() {
		return DiscountResponseVo.builder()
			.discountType(discountType)
			.discountValue(discountValue)
			.build();
	}
}

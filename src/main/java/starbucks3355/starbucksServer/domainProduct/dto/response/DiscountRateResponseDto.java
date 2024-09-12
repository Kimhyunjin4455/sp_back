package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.entity.DiscountType;
import starbucks3355.starbucksServer.domainProduct.vo.response.DiscountRateResponseVo;

@Getter
@Builder
public class DiscountRateResponseDto {
	private DiscountType discountType;
	private Integer discountRate;

	public DiscountRateResponseVo dtoToResponseVo() {
		return DiscountRateResponseVo.builder()
			.discountType(discountType)
			.discountRate(discountRate)
			.build();
	}
}

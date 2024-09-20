package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainProduct.entity.DiscountType;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDefaultDisCount;
import starbucks3355.starbucksServer.domainProduct.vo.response.DiscountResponseVo;

@Getter
@NoArgsConstructor
public class DiscountResponseDto {
	private DiscountType discountType;
	private Integer discountValue;

	@Builder
	public DiscountResponseDto(DiscountType discountType, Integer discountValue) {
		this.discountType = discountType;
		this.discountValue = discountValue;
	}

	public DiscountResponseVo dtoToResponseVo() {
		return DiscountResponseVo.builder()
			.discountType(discountType)
			.discountValue(discountValue)
			.build();
	}

	public static DiscountResponseDto from(ProductDefaultDisCount discount) {
		return DiscountResponseDto.builder()
			.discountType(discount.getDiscountType())
			.discountValue(discount.getDiscountValue())
			.build();
	}
}

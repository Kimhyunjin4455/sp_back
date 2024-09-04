package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.entity.DiscountType;

@Getter
@Builder
public class DiscountResponseDto {
	private DiscountType discountType;
	private Integer value;
}

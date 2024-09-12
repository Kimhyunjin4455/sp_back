package starbucks3355.starbucksServer.domainProduct.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainProduct.entity.DiscountType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountPriceResponseVo {
	private DiscountType discountType;
	private Integer discountPrice;
}

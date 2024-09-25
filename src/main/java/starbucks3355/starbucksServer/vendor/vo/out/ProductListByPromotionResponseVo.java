package starbucks3355.starbucksServer.vendor.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductListByPromotionResponseVo {

	String productUuid;

	@Builder
	public ProductListByPromotionResponseVo(String productUuid) {
		this.productUuid = productUuid;
	}
}

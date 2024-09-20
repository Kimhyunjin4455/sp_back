package starbucks3355.starbucksServer.domainPromotion.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PromotionProductResponseVo {
	String productUuid;

	@Builder
	public PromotionProductResponseVo(String productUuid) {
		this.productUuid = productUuid;
	}
}

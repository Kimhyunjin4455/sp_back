package starbucks3355.starbucksServer.domainPromotion.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PromotionNameResponseVo {
	String promotionName;

	@Builder
	public PromotionNameResponseVo(String promotionName) {
		this.promotionName = promotionName;
	}
}

package starbucks3355.starbucksServer.domainPromotion.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PromotionResponseVo {
	private String promotionUuid;

	@Builder
	public PromotionResponseVo(String promotionUuid) {
		this.promotionUuid = promotionUuid;
	}

}

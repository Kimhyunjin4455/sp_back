package starbucks3355.starbucksServer.domainPromotion.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainPromotion.entity.Promotion;
import starbucks3355.starbucksServer.domainPromotion.vo.out.PromotionNameResponseVo;

@Getter
@NoArgsConstructor
public class PromotionNameResponseDto {
	String promotionName;

	@Builder
	public PromotionNameResponseDto(String promotionName) {
		this.promotionName = promotionName;
	}

	public static PromotionNameResponseDto from(Promotion promotion) {
		return PromotionNameResponseDto.builder()
			.promotionName(promotion.getPromotionName())
			.build();
	}

	public PromotionNameResponseVo dtoToResponseVo() {
		return PromotionNameResponseVo.builder()
			.promotionName(promotionName)
			.build();
	}
}

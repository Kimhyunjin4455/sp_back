package starbucks3355.starbucksServer.domainPromotion.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainPromotion.entity.Promotion;
import starbucks3355.starbucksServer.domainPromotion.vo.out.PromotionResponseVo;

@Getter
@NoArgsConstructor
public class PromotionResponseDto {
	private String promotionUuid;

	@Builder
	public PromotionResponseDto(String promotionUuid) {
		this.promotionUuid = promotionUuid;
	}

	public PromotionResponseVo dtoToResponseVo() {
		return PromotionResponseVo.builder()
			.promotionUuid(promotionUuid)
			.build();
	}

	public static PromotionResponseDto from(Promotion promotion) {
		return PromotionResponseDto.builder()
			.promotionUuid(promotion.getPromotionUuid())
			.build();
	}

}

package starbucks3355.starbucksServer.domainPromotion.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainPromotion.entity.Promotion;
import starbucks3355.starbucksServer.domainPromotion.vo.out.PromotionProductResponseVo;

@Getter
@NoArgsConstructor
public class PromotionProductResponseDto {
	String productUuid;

	@Builder
	public PromotionProductResponseDto(String productUuid) {
		this.productUuid = productUuid;
	}

	public static PromotionProductResponseDto from(Promotion promotion) {
		return PromotionProductResponseDto.builder()
			.productUuid(promotion.getProductUuid())
			.build();
	}

	public PromotionProductResponseVo dtoToResponseVo() {
		return PromotionProductResponseVo.builder()
			.productUuid(productUuid)
			.build();
	}
}

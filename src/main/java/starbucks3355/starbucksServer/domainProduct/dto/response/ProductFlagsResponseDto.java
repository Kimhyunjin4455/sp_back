package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductFlagsResponseVo;

@Getter
@Builder
public class ProductFlagsResponseDto {
	private Boolean isLiked;
	private Boolean isNew;
	private Boolean isBest;

	public ProductFlagsResponseDto(Boolean isLiked, Boolean isNew, Boolean isBest) {
		this.isLiked = isLiked;
		this.isNew = isNew;
		this.isBest = isBest;
	}

	public ProductFlagsResponseVo dtoToResponseVo() {
		return ProductFlagsResponseVo.builder()
			.isLiked(isLiked)
			.isBest(isBest)
			.isNew(isNew)
			.build();
	}
}

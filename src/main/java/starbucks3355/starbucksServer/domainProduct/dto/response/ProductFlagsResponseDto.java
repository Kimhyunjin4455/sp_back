package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainProduct.entity.ProductFlags;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductFlagsResponseVo;

@Getter
@NoArgsConstructor
public class ProductFlagsResponseDto {
	private Boolean isNew;
	private Boolean isBest;

	@Builder
	public ProductFlagsResponseDto(Boolean isNew, Boolean isBest) {
		this.isNew = isNew;
		this.isBest = isBest;
	}

	public ProductFlagsResponseVo dtoToResponseVo() {
		return ProductFlagsResponseVo.builder()
			.isBest(isBest)
			.isNew(isNew)
			.build();
	}

	public static ProductFlagsResponseDto from(ProductFlags productFlags) {
		return ProductFlagsResponseDto.builder()
			.isNew(productFlags.isNew())
			.isBest(productFlags.isBest())
			.build();
	}
}

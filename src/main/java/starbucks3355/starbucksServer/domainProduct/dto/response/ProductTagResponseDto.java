package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainProduct.entity.ProductTag;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductTagResponseVo;

@Getter
@NoArgsConstructor
public class ProductTagResponseDto {
	String tagName;

	@Builder
	public ProductTagResponseDto(String tagName) {
		this.tagName = tagName;
	}

	public ProductTagResponseVo toResponseVo() {
		return ProductTagResponseVo.builder()
			.tagName(tagName)
			.build();
	}

	public static ProductTagResponseDto from(ProductTag productTag) {
		return ProductTagResponseDto.builder()
			.tagName(productTag.getTagName())
			.build();
	}

	public static ProductTagResponseDto fromName(String tagName) {
		return ProductTagResponseDto.builder()
			.tagName(tagName)
			.build();
	}
}

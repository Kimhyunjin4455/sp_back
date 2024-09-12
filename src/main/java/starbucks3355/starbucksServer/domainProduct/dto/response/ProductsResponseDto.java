package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductsResponseVo;

@Getter
@Builder
public class ProductsResponseDto {
	private String productUuid;
	private String productName;
	private String productDescription;
	private String productInfo;

	public ProductsResponseDto(
		String productUuid,
		String productName,
		String productDescription,
		String productInfo
	) {
		this.productUuid = productUuid;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productInfo = productInfo;

	}

	public ProductsResponseVo dtoToResponseVo() {
		return ProductsResponseVo.builder()
			.productUuid(productUuid)
			.productName(productName)
			.productDescription(productDescription)
			.productInfo(productInfo)
			.build();
	}

}

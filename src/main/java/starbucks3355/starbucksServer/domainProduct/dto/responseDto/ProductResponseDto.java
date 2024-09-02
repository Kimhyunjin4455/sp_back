package starbucks3355.starbucksServer.domainProduct.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.vo.ProductResponseVo;

@Getter
public class ProductResponseDto {
	private String productUuid;
	private String productName;
	private String productDescription;
	private String productInfo;
	private Long productCode; // 상품 옵션의 id 필드의 값을 뜻함

	@Builder
	public ProductResponseDto(String productUuid, String productName, String productDescription, String productInfo,
		Long productCode) {
		this.productUuid = productUuid;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productInfo = productInfo;
		this.productCode = productCode;
	}

	public ProductResponseVo dtoToResponseVo() {
		return ProductResponseVo.builder()
			.productUuid(productUuid)
			.productName(productName)
			.productDescription(productDescription)
			.productInfo(productInfo)
			.productCode(productCode)
			.build();
	}

}

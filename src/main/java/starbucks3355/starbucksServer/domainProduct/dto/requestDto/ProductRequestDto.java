package starbucks3355.starbucksServer.domainProduct.dto.requestDto;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.entity.Product;

@Getter
public class ProductRequestDto {
	private String productUuid;
	private String productName;
	private String productDescription;
	private String productInfo;
	private Long productCode; // 상품 옵션의 id 필드의 값을 뜻함

	public Product dtoToEntity(String productUuid) {
		return Product.builder()
			.productUuid(productUuid)
			.productName(productName)
			.productDescription(productDescription)
			.productInfo(productInfo)
			.build();
	}

	@Builder
	public ProductRequestDto(String productUuid, String productName, String productDescription,
		String productInfo, Long productCode) {
		this.productUuid = productUuid;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productInfo = productInfo;
		this.productCode = productCode;
	}
}

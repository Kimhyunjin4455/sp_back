package starbucks3355.starbucksServer.domainProduct.dto.request;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.vo.request.ProductRequestVo;

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

	public static ProductRequestDto of(ProductRequestVo productRequestVo) {
		return ProductRequestDto.builder()
			.productName(productRequestVo.getProductName())
			.productDescription(productRequestVo.getProductDescription())
			.productInfo(productRequestVo.getProductInfo())
			.build();

	}
}

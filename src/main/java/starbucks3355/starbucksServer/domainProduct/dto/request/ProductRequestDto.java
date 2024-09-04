package starbucks3355.starbucksServer.domainProduct.dto.request;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.entity.Product;

@Getter
public class ProductRequestDto {
	private String productUuid;
	private String productName;
	private String productDescription;
	private String productInfo;

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
		String productInfo) {
		this.productUuid = productUuid;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productInfo = productInfo;
	}
}

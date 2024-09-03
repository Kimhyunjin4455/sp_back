package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.entity.DiscountType;
import starbucks3355.starbucksServer.domainProduct.vo.response.ProductResponseVo;

@Getter
public class ProductResponseDto {
	private String productUuid;
	private String productName;
	private String productDescription;
	private String productInfo;
	private Long productCode; // 상품 옵션의 id 필드의 값을 뜻함
	private Integer price;
	private DiscountType discountType;
	private Integer value;
	private String productImg;
	private Double reviewScore;
	private Integer reviewCount;
	private Boolean isChecked;
	private Boolean isNew;
	private Boolean isBest;

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

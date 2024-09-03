package starbucks3355.starbucksServer.domainProduct.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainProduct.entity.DiscountType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseVo {
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
	private Boolean isLiked;
	private Boolean isNew;
	private Boolean isBest;
}

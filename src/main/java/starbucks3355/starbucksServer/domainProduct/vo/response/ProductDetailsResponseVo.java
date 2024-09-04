package starbucks3355.starbucksServer.domainProduct.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsResponseVo {
	private String productUuid; // 상품 옵션의 id 필드의 값을 뜻함
	private Integer price;
}

package starbucks3355.starbucksServer.domainProduct.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductTagResponseVo {
	String tagName;

	@Builder
	public ProductTagResponseVo(String tagName) {
		this.tagName = tagName;
	}
}

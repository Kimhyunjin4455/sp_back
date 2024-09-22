package starbucks3355.starbucksServer.vendor.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryAndCountOfSearchedProductListResponseVo {
	private String topCategoryName;
	private Integer count;

	@Builder
	public CategoryAndCountOfSearchedProductListResponseVo(String topCategoryName, Integer count) {
		this.topCategoryName = topCategoryName;
		this.count = count;
	}
}

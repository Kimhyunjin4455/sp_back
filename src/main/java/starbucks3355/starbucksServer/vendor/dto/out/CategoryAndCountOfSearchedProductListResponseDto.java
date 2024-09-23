package starbucks3355.starbucksServer.vendor.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.vendor.vo.out.CategoryAndCountOfSearchedProductListResponseVo;

@Getter
@NoArgsConstructor
public class CategoryAndCountOfSearchedProductListResponseDto {
	private String topCategoryName;
	private Integer count;

	@Builder
	public CategoryAndCountOfSearchedProductListResponseDto(String topCategoryName, Integer count) {
		this.topCategoryName = topCategoryName;
		this.count = count;
	}

	public CategoryAndCountOfSearchedProductListResponseVo dtoToResponseVo() {
		return CategoryAndCountOfSearchedProductListResponseVo.builder()
			.topCategoryName(topCategoryName)
			.count(count)
			.build();
	}

}

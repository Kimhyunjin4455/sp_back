package starbucks3355.starbucksServer.vendor.dto.out;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.vendor.vo.out.CategoryAndCountOfSearchedProductListResponseVo;

@Getter
@NoArgsConstructor
@ToString
public class CategoryAndCountOfSearchedProductListResponseDto {
	private String topCategoryName;
	private Long count;

	@Builder
	@QueryProjection
	public CategoryAndCountOfSearchedProductListResponseDto(String topCategoryName, Long count) {
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

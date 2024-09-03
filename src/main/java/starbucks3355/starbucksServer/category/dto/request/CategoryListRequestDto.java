package starbucks3355.starbucksServer.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListRequestDto {
	private Integer majorCategoryId;
	private Integer middleCategoryId;
	private Integer bottomCategoryId;
	private Long productCode; // 각 상품개체를 표현하는 값
}
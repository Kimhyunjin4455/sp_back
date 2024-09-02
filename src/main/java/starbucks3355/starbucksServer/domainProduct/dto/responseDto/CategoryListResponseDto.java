package starbucks3355.starbucksServer.domainProduct.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListResponseDto {
	private Long id;
	private Integer majorCategoryId;
	private Integer middleCategoryId;
	private Integer bottomCategoryId;
	private Long productCode;
}

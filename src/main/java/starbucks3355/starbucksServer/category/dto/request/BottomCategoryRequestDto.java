package starbucks3355.starbucksServer.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.category.entity.BottomCategory;
import starbucks3355.starbucksServer.category.entity.MiddleCategory;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BottomCategoryRequestDto {
	private String bottomCategoryName;
	private String bottomCategoryDescription;
	private String middleCategoryCode;

	public BottomCategory toEntity(MiddleCategory middleCategory, String bottomCategoryCode) {
		return BottomCategory.builder()
			.categoryName(bottomCategoryName)
			.categoryDescription(bottomCategoryDescription)
			.categoryCode(bottomCategoryCode)
			.middleCategory(middleCategory)
			.build();

	}
}

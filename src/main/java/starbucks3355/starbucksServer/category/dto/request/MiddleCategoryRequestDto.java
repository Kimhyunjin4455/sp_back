package starbucks3355.starbucksServer.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.category.entity.MiddleCategory;
import starbucks3355.starbucksServer.category.entity.TopCategory;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MiddleCategoryRequestDto {

	private String middleCategoryName;
	//private String middleCategoryDescription;
	//private String topCategoryCode;
	private Integer topCategoryId;

	public MiddleCategory toEntity(TopCategory topCategory) {
		return MiddleCategory.builder()
			//.id(getTopCategoryId())
			.categoryName(middleCategoryName)
			.topCategory(topCategory)
			.build();
	}

}

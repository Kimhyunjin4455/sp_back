<<<<<<<< Updated upstream:src/main/java/starbucks3355/starbucksServer/category/dto/request/MiddleCategoryListRequestDto.java
package starbucks3355.starbucksServer.category.dto.request;
========
package starbucks3355.starbucksServer.domainProduct.dto.request;
>>>>>>>> Stashed changes:src/main/java/starbucks3355/starbucksServer/domainProduct/dto/request/MiddleCategoryListRequestDto.java

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.category.entity.MiddleCategoryList;

@Getter
@NoArgsConstructor
public class MiddleCategoryListRequestDto {
	private Integer topId; // 상위 카테고리 ID
	private String categoryName;
	@NotNull(message = "TopCategory must not be null")
	private TopCategoryListRequestDto topCategory; // dto에서는 굳이 연관된 곳의 객체가 필요 없는지?, 이거 지우고 topId로 처리할지
	private List<BottomCategoryListRequestDto> bottomCategories;

	public MiddleCategoryList dtoToEntity() {
		return MiddleCategoryList.builder()
			.categoryName(categoryName)
			.topCategoryList(topCategory.dtoToEntity())
			.build();
	}

	@Builder
	public MiddleCategoryListRequestDto(Integer topId, String categoryName, TopCategoryListRequestDto topCategoryDto,
		List<BottomCategoryListRequestDto> bottomCategoryListDto) {
		this.topId = topId;
		this.categoryName = categoryName;
		this.topCategory = topCategoryDto;
		this.bottomCategories = (bottomCategoryListDto != null ? bottomCategoryListDto : new ArrayList<>());
	}
}

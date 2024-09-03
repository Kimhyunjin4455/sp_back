<<<<<<<< Updated upstream:src/main/java/starbucks3355/starbucksServer/category/dto/request/TopCategoryListRequestDto.java
package starbucks3355.starbucksServer.category.dto.request;
========
package starbucks3355.starbucksServer.domainProduct.dto.request;
>>>>>>>> Stashed changes:src/main/java/starbucks3355/starbucksServer/domainProduct/dto/request/TopCategoryListRequestDto.java

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.category.entity.TopCategoryList;

@Getter
public class TopCategoryListRequestDto {
	@NotNull(message = "Category name cannot be null")
	private String categoryName;
	private String categoryImg;
	private List<MiddleCategoryListRequestDto> middleCategories;

	public TopCategoryList dtoToEntity() {
		return TopCategoryList.builder()
			.categoryName(categoryName)
			.categoryImg(categoryImg)
			.build();

	}

	@Builder
	public TopCategoryListRequestDto(String categoryName, String categoryImg,
		List<MiddleCategoryListRequestDto> middleCategories) {
		this.categoryName = categoryName;
		this.categoryImg = categoryImg;
		this.middleCategories = (middleCategories != null ? middleCategories : new ArrayList<>());
	}

}

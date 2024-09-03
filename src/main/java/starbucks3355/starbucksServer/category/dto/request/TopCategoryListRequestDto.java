package starbucks3355.starbucksServer.category.dto.request;

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

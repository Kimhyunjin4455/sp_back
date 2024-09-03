package starbucks3355.starbucksServer.category.dto.request;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.category.entity.MiddleCategory;

@Getter
@NoArgsConstructor
public class MiddleCategoryRequestDto {
	private Integer topId; // 상위 카테고리 ID
	private String categoryName;
	@NotNull(message = "TopCategory must not be null")
	private TopCategoryRequestDto topCategory; // dto에서는 굳이 연관된 곳의 객체가 필요 없는지?, 이거 지우고 topId로 처리할지
	private List<BottomCategoryRequestDto> bottomCategories;

	public MiddleCategory dtoToEntity() {
		return MiddleCategory.builder()
			.categoryName(categoryName)
			.topCategory(topCategory.dtoToEntity())
			.build();
	}

	@Builder
	public MiddleCategoryRequestDto(Integer topId, String categoryName, TopCategoryRequestDto topCategoryDto,
		List<BottomCategoryRequestDto> bottomCategoryListDto) {
		this.topId = topId;
		this.categoryName = categoryName;
		this.topCategory = topCategoryDto;
		this.bottomCategories = (bottomCategoryListDto != null ? bottomCategoryListDto : new ArrayList<>());
	}
}

package starbucks3355.starbucksServer.category.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FullMiddleCategoryResponseDto {

	private Integer id;
	private String middleCategoryName;
	private List<BottomCategoryResponseDto> bottomCategoryList; // 하위 카테고리 목록

	@Builder
	public FullMiddleCategoryResponseDto(Integer id, String middleCategoryName,
		List<BottomCategoryResponseDto> bottomCategoryList) {
		this.id = id;
		this.middleCategoryName = middleCategoryName;
		this.bottomCategoryList = bottomCategoryList;
	}
}

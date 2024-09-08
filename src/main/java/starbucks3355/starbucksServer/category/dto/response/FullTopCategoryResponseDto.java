package starbucks3355.starbucksServer.category.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FullTopCategoryResponseDto {
	private Integer id;
	private String topCategoryName;
	private List<MiddleCategoryResponseDto> middleCategoryList; // 중간 카테고리 목록

	@Builder
	public FullTopCategoryResponseDto(Integer id, String topCategoryName,
		List<MiddleCategoryResponseDto> middleCategoryList) {
		this.id = id;
		this.topCategoryName = topCategoryName;
		this.middleCategoryList = middleCategoryList;
	}
}

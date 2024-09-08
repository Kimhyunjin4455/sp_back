package starbucks3355.starbucksServer.category.vo.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FullTopCategoryResponseVo {
	
	private Integer id;
	private String topCategoryName;
	private List<MiddleCategoryResponseVo> middleCategoryList; // 중간 카테고리 목록

	@Builder
	public FullTopCategoryResponseVo(Integer id, String topCategoryName,
		List<MiddleCategoryResponseVo> middleCategoryList) {
		this.id = id;
		this.topCategoryName = topCategoryName;
		this.middleCategoryList = middleCategoryList;
	}
}

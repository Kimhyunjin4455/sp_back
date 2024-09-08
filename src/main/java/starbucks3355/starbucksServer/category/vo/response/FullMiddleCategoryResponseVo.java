package starbucks3355.starbucksServer.category.vo.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class FullMiddleCategoryResponseVo {
	private Integer id;
	private String middleCategoryName;
	private List<BottomCategoryResponseVo> bottomCategoryList; // 하위 카테고리 목록

	@Builder
	public FullMiddleCategoryResponseVo(Integer id, String middleCategoryName,
		List<BottomCategoryResponseVo> bottomCategoryList) {
		this.id = id;
		this.middleCategoryName = middleCategoryName;
		this.bottomCategoryList = bottomCategoryList;
	}
}

package starbucks3355.starbucksServer.category.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FullBottomCategoryResponseVo {
	private Integer id;
	private String bottomCategoryName;

	@Builder
	public FullBottomCategoryResponseVo(Integer id, String bottomCategoryName) {
		this.id = id;
		this.bottomCategoryName = bottomCategoryName;
	}
}

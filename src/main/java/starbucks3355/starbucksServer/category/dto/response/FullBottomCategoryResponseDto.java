package starbucks3355.starbucksServer.category.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FullBottomCategoryResponseDto {
	private Integer id;
	private String bottomCategoryName;

	@Builder
	public FullBottomCategoryResponseDto(Integer id, String bottomCategoryName) {
		this.id = id;
		this.bottomCategoryName = bottomCategoryName;
	}
}

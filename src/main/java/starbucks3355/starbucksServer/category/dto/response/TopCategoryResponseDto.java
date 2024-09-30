package starbucks3355.starbucksServer.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.category.vo.response.TopCategoryResponseVo;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopCategoryResponseDto {

	private Integer id;
	private String topCategoryName;

	public TopCategoryResponseVo toVo() {
		return TopCategoryResponseVo.builder()
			.id(id)
			.topCategoryName(topCategoryName)
			.build();
	}
}

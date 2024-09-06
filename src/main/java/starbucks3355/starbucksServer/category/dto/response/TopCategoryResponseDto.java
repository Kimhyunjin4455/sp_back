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

	private String topCategoryCode;
	private String topCategoryName;
	private String topCategoryDescription;

	public TopCategoryResponseVo toVo() {
		return TopCategoryResponseVo.builder()
			.topCategoryCode(topCategoryCode)
			.topCategoryName(topCategoryName)
			.topCategoryDescription(topCategoryDescription)
			.build();
	}
}

package starbucks3355.starbucksServer.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.category.vo.response.BottomCategoryResponseVo;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BottomCategoryResponseDto {
	//private String bottomCategoryCode;
	private String bottomCategoryName;
	private Integer id;
	//private String bottomCategoryDescription;

	public BottomCategoryResponseVo toVo() {
		return BottomCategoryResponseVo.builder()
			.id(id)
			.bottomCategoryName(bottomCategoryName)
			//.bottomCategoryDescription(bottomCategoryDescription)
			.build();
	}
}

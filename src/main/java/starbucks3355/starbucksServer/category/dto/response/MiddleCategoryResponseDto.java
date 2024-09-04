package starbucks3355.starbucksServer.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.category.vo.response.MiddleCategoryResponseVo;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiddleCategoryResponseDto {
	private String middleCategoryCode;
	private String middleCategoryName;
	private String middleCategoryDescription;

	public MiddleCategoryResponseVo toVo() {
		return MiddleCategoryResponseVo.builder()
			.middleCategoryCode(middleCategoryCode)
			.middleCategoryName(middleCategoryName)
			.middleCategoryDescription(middleCategoryDescription)
			.build();
	}
}

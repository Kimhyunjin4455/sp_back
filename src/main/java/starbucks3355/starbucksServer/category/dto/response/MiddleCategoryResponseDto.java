package starbucks3355.starbucksServer.category.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonPropertyOrder({"id", "middleCategoryName", "bottomCategories"})
public class MiddleCategoryResponseDto {
	//private String middleCategoryCode;
	private Integer id;
	private String middleCategoryName;

	//@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<BottomCategoryResponseDto> bottomCategories;

	//private String middleCategoryDescription;

	public MiddleCategoryResponseVo toVo() {
		return MiddleCategoryResponseVo.builder()
			.id(id)
			.middleCategoryName(middleCategoryName)
			//.middleCategoryDescription(middleCategoryDescription)
			.build();
	}
}

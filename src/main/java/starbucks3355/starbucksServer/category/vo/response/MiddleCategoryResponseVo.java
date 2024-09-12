package starbucks3355.starbucksServer.category.vo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.category.dto.response.BottomCategoryResponseDto;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiddleCategoryResponseVo {
	//private String middleCategoryCode;
	private String middleCategoryName;
	private Integer id;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<BottomCategoryResponseDto> bottomCategories;

	//private String middleCategoryDescription;
}

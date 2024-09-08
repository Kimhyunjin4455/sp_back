package starbucks3355.starbucksServer.category.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BottomCategoryResponseVo {
	//private String bottomCategoryCode;
	private Integer id;
	private String bottomCategoryName;

	//private String bottomCategoryDescription;
}

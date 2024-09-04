package starbucks3355.starbucksServer.category.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopCategoryResponseVo {

	private String topCategoryCode;
	private String topCategoryName;
	private String topCategoryDescription;
}

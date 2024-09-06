package starbucks3355.starbucksServer.category.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiddleCategoryResponseVo {
	private String middleCategoryCode;
	private String middleCategoryName;
	private String middleCategoryDescription;
}

package starbucks3355.starbucksServer.domainProduct.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopCategoryListResponseDto {
	private Integer id;
	private String categoryName;
	private String categoryImg;
}

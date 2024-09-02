package starbucks3355.starbucksServer.domainProduct.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiddleCategoryListResponseDto {
	private Integer id;
	private String categoryName;
	private Integer topId; // 상위 카테고리 ID
}

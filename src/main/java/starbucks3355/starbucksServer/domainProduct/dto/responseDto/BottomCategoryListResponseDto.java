package starbucks3355.starbucksServer.domainProduct.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BottomCategoryListResponseDto {
	private Long id;
	private String categoryName;
	private Long middleId; // 상위 중간 카테고리 ID
}

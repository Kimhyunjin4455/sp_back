package starbucks3355.starbucksServer.category.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BottomCategoryRequestDto {
	private Long middleId; // 상위 중간 카테고리 ID
	private String categoryName;
	@NotNull(message = "MiddleCategory must not be null")
	private MiddleCategoryRequestDto middleCategory;
}

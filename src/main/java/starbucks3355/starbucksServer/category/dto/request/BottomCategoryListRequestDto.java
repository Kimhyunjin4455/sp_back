<<<<<<<< Updated upstream:src/main/java/starbucks3355/starbucksServer/category/dto/request/BottomCategoryListRequestDto.java
package starbucks3355.starbucksServer.category.dto.request;
========
package starbucks3355.starbucksServer.domainProduct.dto.request;
>>>>>>>> Stashed changes:src/main/java/starbucks3355/starbucksServer/domainProduct/dto/request/BottomCategoryListRequestDto.java

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BottomCategoryListRequestDto {
	private Long middleId; // 상위 중간 카테고리 ID
	private String categoryName;
	@NotNull(message = "MiddleCategory must not be null")
	private MiddleCategoryListRequestDto middleCategory;
}

package starbucks3355.starbucksServer.category.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor

@ToString
@Builder
public class MiddleCategoryListResponseDto {
	private List<MiddleCategoryResponseDto> middleCategories;

	@Builder
	public MiddleCategoryListResponseDto(List<MiddleCategoryResponseDto> middleCategories) {
		this.middleCategories = middleCategories;
	}
}

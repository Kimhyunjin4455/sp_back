package starbucks3355.starbucksServer.category.sevice;

import java.util.List;

import starbucks3355.starbucksServer.category.dto.request.BottomCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.MiddleCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.response.BottomCategoryResponseDto;
import starbucks3355.starbucksServer.category.dto.response.MiddleCategoryResponseDto;
import starbucks3355.starbucksServer.category.dto.response.TopCategoryResponseDto;

public interface CategoryService {
	void createTopCategory(TopCategoryRequestDto topCategoryRequestDto);

	void createMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto);

	void createBottomCategory(BottomCategoryRequestDto bottomCategoryRequestDto);

	List<TopCategoryResponseDto> getTopCategories();

	List<MiddleCategoryResponseDto> getMiddleCategories(String topCategoryCode);

	List<BottomCategoryResponseDto> getBottomCategories(String middleCategoryCode);
}

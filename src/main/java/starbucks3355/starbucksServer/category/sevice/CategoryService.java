package starbucks3355.starbucksServer.category.sevice;

import starbucks3355.starbucksServer.category.dto.request.BottomCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.CategoryListRequestDto;
import starbucks3355.starbucksServer.category.dto.request.MiddleCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryRequestDto;

public interface CategoryService {
	void addCategory(CategoryListRequestDto categorytDto,
		TopCategoryRequestDto topCategoryDto,
		MiddleCategoryRequestDto middleCategoryDto,
		BottomCategoryRequestDto bottomCategoryDto);
}

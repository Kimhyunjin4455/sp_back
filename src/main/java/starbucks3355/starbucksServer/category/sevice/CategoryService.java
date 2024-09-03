package starbucks3355.starbucksServer.category.sevice;

import starbucks3355.starbucksServer.category.dto.request.BottomCategoryListRequestDto;
import starbucks3355.starbucksServer.category.dto.request.CategoryListRequestDto;
import starbucks3355.starbucksServer.category.dto.request.MiddleCategoryListRequestDto;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryListRequestDto;

public interface CategoryService {
	void addCategory(CategoryListRequestDto categorytDto,
		TopCategoryListRequestDto topCategoryDto,
		MiddleCategoryListRequestDto middleCategoryDto,
		BottomCategoryListRequestDto bottomCategoryDto);
}

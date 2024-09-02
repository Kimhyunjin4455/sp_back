package starbucks3355.starbucksServer.domainProduct.service;

import starbucks3355.starbucksServer.domainProduct.dto.requestDto.BottomCategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.requestDto.CategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.requestDto.MiddleCategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.requestDto.TopCategoryListRequestDto;

public interface CategoryService {
	void addCategory(CategoryListRequestDto categorytDto,
		TopCategoryListRequestDto topCategoryDto,
		MiddleCategoryListRequestDto middleCategoryDto,
		BottomCategoryListRequestDto bottomCategoryDto);
}

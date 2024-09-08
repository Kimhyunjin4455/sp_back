package starbucks3355.starbucksServer.category.sevice;

import java.util.List;

import starbucks3355.starbucksServer.category.dto.request.MiddleCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.response.MiddleCategoryResponseDto;
import starbucks3355.starbucksServer.category.dto.response.TopCategoryResponseDto;

public interface CategoryService {
	void createTopCategory(TopCategoryRequestDto topCategoryRequestDto);

	void createMiddleCategory(List<MiddleCategoryRequestDto> middleCategoryRequestDto);

	//void createBottomCategory(BottomCategoryRequestDto bottomCategoryRequestDto);

	// 대 카테고리 조회
	List<TopCategoryResponseDto> getTopCategories();

	// 중 카테고리 조회
	//List<MiddleCategoryResponseDto> getMiddleCategories(String topCategoryCode);

	// 소 카테고리 조회
	//List<BottomCategoryResponseDto> getBottomCategories(String middleCategoryCode);

	// 단일 카테고리 조회
	//TopCategoryResponseDto getTopCategoryByCategoryCode(String topCategoryCode);

	//MiddleCategoryResponseDto getMiddleCategoryByCategoryCode(String middleCategoryCode);

	//BottomCategoryResponseDto getBottomCategoryByCategoryCode(String bottomCategoryCode);

	// 탑 카테고리 업데이트
	//void updateTopCategory(TopCategoryRequestDto topCategoryRequestDto, String topCategoryName);

	//void updateMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto, String middleCategoryName);

	//void updateBottomCategory(BottomCategoryRequestDto bottomCategoryRequestDto, String bottomCategoryName,
	//String middleCategoryCode);

	// topCategoryid로 middleCategory 조회
	List<MiddleCategoryResponseDto> getMiddleCategoriesByTopCategoryId(Integer topCategoryId);
}

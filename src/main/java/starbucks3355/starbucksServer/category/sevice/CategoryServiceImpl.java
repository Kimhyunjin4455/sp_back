package starbucks3355.starbucksServer.category.sevice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryRequestDto;
import starbucks3355.starbucksServer.category.repository.BottomCategoryRepository;
import starbucks3355.starbucksServer.category.repository.MiddleCategoryRepository;
import starbucks3355.starbucksServer.category.repository.TopCategoryRepository;
import starbucks3355.starbucksServer.common.utils.CategoryCodeGenerator;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final TopCategoryRepository topCategoryRepository;
	private final MiddleCategoryRepository middleCategoryRepository;
	private final BottomCategoryRepository bottomCategoryRepository;

	private static final int MAX_CODE_TRIES = 5; // 최대 재시도 횟수

	@Transactional
	@Override
	public void createTopCategory(TopCategoryRequestDto topCategoryRequestDto) {
		if (topCategoryRepository.existsByCategoryName(topCategoryRequestDto.getTopCategoryName())) {
			throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
		}

		String categoryCode = generateUniqueCategoryCode("TC-");
	}

	// @Transactional
	// @Override
	// public void createMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto) {
	// 	if (topCategoryRepository.existsByCategoryName())
	//
	// }
	//
	// @Transactional
	// @Override
	// public void createBottomCategory(BottomCategoryRequestDto bottomCategoryRequestDto) {
	//
	// }

	private String generateUniqueCategoryCode(String prefix) {
		for (int i = 0; i < MAX_CODE_TRIES; i++) {
			String categoryCode = CategoryCodeGenerator.generateCategoryCode(prefix);
			switch (prefix) {
				case "TC-":
					if (!topCategoryRepository.existsByCategoryCode(categoryCode)) {
						return categoryCode;  // 중복이 없으면 코드 반환
					}
					break;
				case "MC-":
					if (!middleCategoryRepository.existsByCategoryCode(categoryCode)) {
						return categoryCode;  // 중복이 없으면 코드 반환
					}
					break;
				case "BC-":
					if (!bottomCategoryRepository.existsByCategoryCode(categoryCode)) {
						return categoryCode;  // 중복이 없으면 코드 반환
					}
					break;
				default:
					throw new IllegalArgumentException("유효하지 않은 카테고리 코드 접두사입니다: " + prefix);
			}

		}
		throw new IllegalStateException("고유한 카테고리 코드를 생성하는 데 실패했습니다.");
	}
}

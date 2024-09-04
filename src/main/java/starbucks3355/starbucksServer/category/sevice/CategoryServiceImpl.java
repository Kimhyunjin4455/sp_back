package starbucks3355.starbucksServer.category.sevice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.category.dto.request.BottomCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.MiddleCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryRequestDto;
import starbucks3355.starbucksServer.category.entity.MiddleCategory;
import starbucks3355.starbucksServer.category.entity.TopCategory;
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

		// DTO를 엔티티 변환해서 생성된 카테고리 코드 주입
		TopCategory topCategory = topCategoryRequestDto.toEntity(categoryCode);
		topCategoryRepository.save(topCategory);
		log.info("Top 카테고리 코드: " + topCategory.getCategoryCode());

		try {
			topCategoryRepository.save(topCategoryRequestDto.toEntity(categoryCode));
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error occurred", e);
			throw new RuntimeException("카테고리 생성 중 오류가 발생했습니다.", e);
		}
	}

	@Transactional
	@Override
	public void createMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto) {
		if (middleCategoryRepository.existsByCategoryName(middleCategoryRequestDto.getMiddleCategoryName())) {
			throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
		}

		try { // Top 카테고리 코드가 존재하는지 확인
			TopCategory topCategory = topCategoryRepository.findByCategoryCode(
				middleCategoryRequestDto.getTopCategoryCode()).orElseThrow(
				() -> new IllegalArgumentException("존재하지 않는 Top 카테고리 코드입니다.")
			); //
			String categoryCode = generateUniqueCategoryCode("MC-");

			// Topcategory도 객체화 시키는 이유는 middlecategory가 속할 topcategory를 지정해주는거임
			middleCategoryRepository.save(middleCategoryRequestDto.toEntity(topCategory, categoryCode));
			log.info("Middle 카테고리 코드: " + categoryCode);
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error occurred", e);
			throw new RuntimeException("카테고리 생성 중 오류가 발생했습니다.", e);
		}

	}

	@Transactional
	@Override
	public void createBottomCategory(BottomCategoryRequestDto bottomCategoryRequestDto) {
		if (bottomCategoryRepository.existsByCategoryName(bottomCategoryRequestDto.getBottomCategoryName())) {
			throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
		}
		try { // Middle 카테고리 코드가 존재하는지 확인
			MiddleCategory middleCategory = middleCategoryRepository.findByCategoryCode(
				bottomCategoryRequestDto.getMiddleCategoryCode()).orElseThrow(
				() -> new IllegalArgumentException("존재하지 않는 Middle 카테고리 코드입니다.")
			);

			String categoryCode = generateUniqueCategoryCode("BC-");
			bottomCategoryRepository.save(bottomCategoryRequestDto.toEntity(middleCategory, categoryCode));
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error occurred", e);
			throw new RuntimeException("카테고리 생성 중 오류가 발생했습니다.", e);
		}

	}

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

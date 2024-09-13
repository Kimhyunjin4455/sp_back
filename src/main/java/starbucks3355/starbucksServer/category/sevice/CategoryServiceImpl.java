package starbucks3355.starbucksServer.category.sevice;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.category.dto.request.BottomCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.MiddleCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.response.BottomCategoryResponseDto;
import starbucks3355.starbucksServer.category.dto.response.MiddleCategoryListResponseDto;
import starbucks3355.starbucksServer.category.dto.response.MiddleCategoryResponseDto;
import starbucks3355.starbucksServer.category.dto.response.TopCategoryResponseDto;
import starbucks3355.starbucksServer.category.entity.BottomCategory;
import starbucks3355.starbucksServer.category.entity.MiddleCategory;
import starbucks3355.starbucksServer.category.entity.TopCategory;
import starbucks3355.starbucksServer.category.repository.BottomCategoryRepository;
import starbucks3355.starbucksServer.category.repository.MiddleCategoryRepository;
import starbucks3355.starbucksServer.category.repository.TopCategoryRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final TopCategoryRepository topCategoryRepository;
	private final MiddleCategoryRepository middleCategoryRepository;
	private final BottomCategoryRepository bottomCategoryRepository;

	// private static final int MAX_CODE_TRIES = 5; // 최대 재시도 횟수
	// private final CategoryListRepository categoryListRepository;
	// private final OrderRepository orderRepository;

	@Transactional
	@Override
	public void createTopCategory(TopCategoryRequestDto topCategoryRequestDto) {
		if (topCategoryRepository.existsByCategoryName(topCategoryRequestDto.getTopCategoryName())) {
			throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
		}

		// DTO를 엔티티 변환해서 생성된 카테고리 코드 주입
		TopCategory topCategory = topCategoryRequestDto.toEntity(topCategoryRequestDto.getTopCategoryName());
		try {
			topCategoryRepository.save(topCategory);

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
	public void createMiddleCategory(List<MiddleCategoryRequestDto> middleCategoryRequestDto) {
		// 중복체크 : topcategoryid와 middlecategoryname을 동시에 검사
		for (MiddleCategoryRequestDto dto : middleCategoryRequestDto) {
			log.info("topcategoryid : {}, categoryname: {}", dto.getTopCategoryId(), dto.getMiddleCategoryName());
			if (middleCategoryRepository.existsByTopCategoryIdAndCategoryName(
				dto.getTopCategoryId(),
				dto.getMiddleCategoryName())) {
				throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
			}

			try { // Top 카테고리 코드가 존재하는지 확인
				TopCategory topCategory = topCategoryRepository.findById(
					dto.getTopCategoryId()).orElseThrow(
					() -> new IllegalArgumentException("존재하지 않는 Top 카테고리 id 입니다.")
				); //

				// Topcategory도 객체화 시키는 이유는 middlecategory가 속할 topcategory를 지정해주는거임
				middleCategoryRepository.save(dto.toEntity(topCategory));
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				throw e;
			} catch (Exception e) {
				log.error("Unexpected error occurred", e);
				throw new RuntimeException("카테고리 생성 중 오류가 발생했습니다.", e);
			}
		}

	}

	@Transactional
	@Override
	public void createBottomCategory(List<BottomCategoryRequestDto> bottomCategoryRequestDto) {

		for (BottomCategoryRequestDto dto : bottomCategoryRequestDto) {
			log.info("middlecategoryid : {}, categoryname: {}", dto.getMiddleCategoryId(), dto.getBottomCategoryName());
			if (bottomCategoryRepository.existsByMiddleCategoryIdAndCategoryName(
				dto.getMiddleCategoryId(),
				dto.getBottomCategoryName())) {
				throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
			}

			try { // Middle 카테고리 코드가 존재하는지 확인
				MiddleCategory middleCategory = middleCategoryRepository.findById(
					dto.getMiddleCategoryId()).orElseThrow(
					() -> new IllegalArgumentException("존재하지 않는 Middle 카테고리 id 입니다.")
				);

				bottomCategoryRepository.save(dto.toEntity(middleCategory));
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				throw e;
			} catch (Exception e) {
				log.error("Unexpected error occurred", e);
				throw new RuntimeException("카테고리 생성 중 오류가 발생했습니다.", e);
			}
		}

	}

	// top 전체 카테고리 조회
	@Override
	// top 카테고리 조회
	// db에 저장 돼 있는 객체를 dto로 변환 시키는 과정
	public List<TopCategoryResponseDto> getTopCategories() {
		return topCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream().map(
			topCategory -> TopCategoryResponseDto.builder()
				.id(topCategory.getId())
				.topCategoryName(topCategory.getCategoryName())
				.build()
		).toList();
	}

	// middle 전체 카테고리 조회
	@Override
	public List<MiddleCategoryResponseDto> getMiddleCategories() {

		return middleCategoryRepository.findAll().stream().map(
			middleCategory -> MiddleCategoryResponseDto.builder()
				.id(middleCategory.getId())
				.middleCategoryName(middleCategory.getCategoryName())
				.build()
		).toList();
	}

	// bottom 전체 카테고리 조회
	@Override
	public List<BottomCategoryResponseDto> getBottomCategories() {
		return bottomCategoryRepository.findAll().stream().map(
			bottomCategory -> BottomCategoryResponseDto.builder()
				.id(bottomCategory.getId())
				.bottomCategoryName(bottomCategory.getCategoryName())
				.build()
		).toList();
	}

	// 미들 카테고리 Name = '카테고리' 목록 조회
	@Override
	@Transactional(readOnly = true)
	public MiddleCategoryListResponseDto getMiddleCategoryByNameAndTopCategoryId(Integer topCategoryId,
		String middleCategoryName) {
		// try {
		// top 카테고리 id로 middle 카테고리 조회
		List<MiddleCategory> middleCategories = middleCategoryRepository.findByTopCategoryIdAndCategoryNameUsingQuery(
			topCategoryId, middleCategoryName);

		if (middleCategories.isEmpty()) {
			return new MiddleCategoryListResponseDto(Collections.emptyList());
		}
		// 각 MiddleCategory에 속한 BottomCategory 목록 조회 및 반환
		List<MiddleCategoryResponseDto> middleCategoryResponseDtos = middleCategories.stream().map(
			middleCategory -> {
				List<BottomCategory> bottomCategories = bottomCategoryRepository.findByMiddleCategoryId(
					middleCategory.getId());
				// BottomCategoryResponseDto로 변환
				List<BottomCategoryResponseDto> bottomCategoryResponseDtos = bottomCategories.stream().map(
					bottomCategory -> BottomCategoryResponseDto.builder()
						.id(bottomCategory.getId())
						.bottomCategoryName(bottomCategory.getCategoryName())
						.build()
				).toList();
				// MiddleCategoryResponseDto로 변환
				return MiddleCategoryResponseDto.builder()
					.id(middleCategory.getId())
					.middleCategoryName(middleCategory.getCategoryName())
					.bottomCategories(bottomCategoryResponseDtos)
					.build();

			}).toList();

		return new MiddleCategoryListResponseDto(middleCategoryResponseDtos);
		// BottomCategory 목록 조회
		// List<BottomCategory> bottomCategories = bottomCategoryRepository.findByMiddleCategoryId(
		// 	middleCategories.getId());
		//
		// //BottomCategoryResponseDto로 변환
		// List<BottomCategoryResponseDto> bottomCategoryResponseDtos = bottomCategories.stream().map(
		// 	bottomCategory -> BottomCategoryResponseDto.builder()
		// 		.id(bottomCategory.getId())
		// 		.bottomCategoryName(bottomCategory.getCategoryName())
		// 		.build()
		// ).toList();
		//
		// // MiddleCategory와 함께 BottomCategory 목록을 포함한 DTO 반환
		// return MiddleCategoryResponseDto.builder()
		// 	.id(middleCategories.getId())
		// 	.middleCategoryName(middleCategories.getCategoryName())
		// 	.bottomCategories(bottomCategoryResponseDtos)
		// 	.build();
		// } catch (IllegalArgumentException e) {
		// 	log.error(e.getMessage());
		// 	throw e;
		// } catch (Exception e) {
		// 	log.error("Unexpected error occurred", e);
		// 	throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
		// }
	}

	// @Override
	// @Transactional
	// // 단일 top 카테고리 조회
	// public TopCategoryResponseDto getTopCategoryByCategoryCode(String topCategoryCode) {
	// 	try {
	// 		TopCategory topCategory = topCategoryRepository.findByCategoryCode(topCategoryCode)
	// 			.orElseThrow(() -> new IllegalArgumentException("해당하지 않는 Top 카테고리 코드입니다."));
	// 		// 디비에 있는 값 객체화 시키기
	// 		return TopCategoryResponseDto.builder()
	// 			.id(topCategory.getId())
	// 			.topCategoryName(topCategory.getCategoryName())
	// 			.build();
	// 	} catch (IllegalArgumentException e) {
	// 		log.error(e.getMessage());
	// 		throw e;
	// 	} catch (Exception e) {
	// 		log.error("Unexpected error occurred", e);
	// 		throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
	// 	}
	//
	// }

	// // middle 전체 카테고리 조회
	// @Override
	// @Transactional(readOnly = true) // 임시로 name으로 수정 변수값
	// public List<MiddleCategoryResponseDto> getMiddleCategories(String topCategoryName) {
	// 	// 미들 카테고리는 조회할때 탑 카테고리의 코드가 있는지 확인을 해야함
	// 	try {
	// 		TopCategory topCategory = topCategoryRepository.findByCategoryName(topCategoryName)
	// 			.orElseThrow(() -> new IllegalArgumentException("해당하지 않는 Top 카테고리 이름입니다."));
	// 		log.info("topCategory : {}", topCategory);
	//
	// 		List<MiddleCategoryResponseDto> middleCategoryResponseDtos = middleCategoryRepository
	// 			.findByTopCategoryCategoryCode(topCategory.getCategoryCode())
	// 			.stream()
	// 			.map(
	// 				middleCategory -> MiddleCategoryResponseDto.builder()
	// 					.middleCategoryCode(middleCategory.getCategoryCode())
	// 					.middleCategoryDescription(middleCategory.getCategoryDescription())
	// 					.middleCategoryName(middleCategory.getCategoryName())
	// 					.build())
	// 			.collect(Collectors.toList());
	// 		return middleCategoryResponseDtos; // db의 저장된 객체를 dto로 변환해서 반환
	// 	} catch (IllegalArgumentException e) {
	// 		log.error(e.getMessage());
	// 		throw e;
	// 	} catch (Exception e) {
	// 		log.error("Unexpected error occurred", e);
	// 		throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
	// 	}
	// }

	// 단일 middle 카테고리 조회
	// @Override
	// @Transactional
	// public MiddleCategoryResponseDto getMiddleCategoryByCategoryCode(String middleCategoryCode) {
	// 	try {
	// 		MiddleCategory middleCategory = middleCategoryRepository.findByCategoryCode(middleCategoryCode)
	// 			.orElseThrow(() -> new IllegalArgumentException("해당하지 않는 Middle 카테고리 코드입니다."));
	// 		return MiddleCategoryResponseDto.builder()
	// 			.middleCategoryCode(middleCategory.getCategoryCode())
	// 			.middleCategoryDescription(middleCategory.getCategoryDescription())
	// 			.middleCategoryName(middleCategory.getCategoryName())
	// 			.build();
	// 	} catch (IllegalArgumentException e) {
	// 		log.error(e.getMessage());
	// 		throw e;
	// 	} catch (Exception e) {
	// 		log.error("Unexpected error occurred", e);
	// 		throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
	// 	}
	// }

	// bottom 전체 카테고리 조회
	// @Override
	// @Transactional(readOnly = true) //middleCategoryCode -> middleCategoryName으로 수정 (임시 테스트)
	// public List<BottomCategoryResponseDto> getBottomCategories(String middleCategoryCode) {
	// 	try {
	// 		MiddleCategory middleCategory = middleCategoryRepository.findByCategoryCode(middleCategoryCode)
	// 			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Middle 카테고리 코드입니다."));
	//
	// 		List<BottomCategoryResponseDto> bottomCategoryResponseDtos = bottomCategoryRepository
	// 			.findByMiddleCategoryCategoryCode(middleCategory.getCategoryCode())
	// 			.stream()
	// 			.map(
	// 				bottomCategory -> BottomCategoryResponseDto.builder()
	// 					.bottomCategoryCode(bottomCategory.getCategoryCode())
	// 					.bottomCategoryDescription(bottomCategory.getCategoryDescription())
	// 					.bottomCategoryName(bottomCategory.getCategoryName())
	// 					.build())
	// 			.collect(Collectors.toList());
	// 		return bottomCategoryResponseDtos;
	//
	// 	} catch (IllegalArgumentException e) {
	// 		log.error(e.getMessage());
	// 		throw e;
	// 	} catch (Exception e) {
	// 		log.error("Unexpected error occurred", e);
	// 		throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
	// 	}
	// }

	// @Override
	// @Transactional
	// public BottomCategoryResponseDto getBottomCategoryByCategoryCode(String bottomCategoryCode) {
	// 	try {
	// 		BottomCategory bottomCategory = bottomCategoryRepository.findByCategoryCode(bottomCategoryCode)
	// 			.orElseThrow(() -> new IllegalArgumentException("해당하지 않는 Bottom 카테고리 코드입니다."));
	// 		return BottomCategoryResponseDto.builder()
	// 			.bottomCategoryCode(bottomCategory.getCategoryCode())
	// 			.bottomCategoryDescription(bottomCategory.getCategoryDescription())
	// 			.bottomCategoryName(bottomCategory.getCategoryName())
	// 			.build();
	// 	} catch (IllegalArgumentException e) {
	// 		log.error(e.getMessage());
	// 		throw e;
	// 	} catch (Exception e) {
	// 		log.error("Unexpected error occurred", e);
	// 		throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
	// 	}
	// }

	@Override
	@Transactional(readOnly = true)
	public List<MiddleCategoryResponseDto> getMiddleCategoriesByTopCategoryId(Integer topCategoryId) {
		List<MiddleCategory> middleCategories = middleCategoryRepository.findByTopCategoryId(topCategoryId);
		// db에 저장된 객체를 dto로 변환해서 반환
		// middle entity를 dto로 변환
		try {
			TopCategory topCategory = topCategoryRepository.findById(topCategoryId)
				.orElseThrow(() -> new IllegalArgumentException("존재 않는 Top 카테고리 ID입니다."));
			log.info("topCategory : {}", topCategory);

			return middleCategories.stream().map(
				middleCategory -> MiddleCategoryResponseDto.builder()
					.id(middleCategory.getId())
					.middleCategoryName(middleCategory.getCategoryName())
					.build()
			).toList();

		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error occurred", e);
			throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
		}
	}

	@Override
	public List<BottomCategoryResponseDto> getBottomCategoriesByMiddleCategoryId(Integer middleCategoryId) {
		List<BottomCategory> bottomCategories = bottomCategoryRepository.findByMiddleCategoryId(middleCategoryId);
		// db에 저장된 객체를 dto로 변환해서 반환

		try {
			MiddleCategory middleCategory = middleCategoryRepository.findById(middleCategoryId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Middle 카테고리 ID입니다."));
			log.info("middleCategory : {}", middleCategory);
			// bottom db entity를 dto로 변환
			return bottomCategories.stream().map(
				bottomCategory -> BottomCategoryResponseDto.builder()
					.id(bottomCategory.getId())
					.bottomCategoryName(bottomCategory.getCategoryName())
					.build()
			).toList();
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error occurred", e);
			throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
		}
	}

	// 모든 카테고리 조회
	// @Override
	// @Transactional(readOnly = true)
	// public List<FullTopCategoryResponseDto> getAllCategories() {
	// 	List<TopCategory> topCategories = topCategoryRepository.findAll();
	// 	return topCategories.stream().map(
	// 		topCategory -> {
	// 			List<MiddleCategory> middleCategories = middleCategoryRepository.findByTopCategoryId(topCategory.getId());
	// 		}
	// }

	// private String generateUniqueCategoryCode(String prefix) {
	// 	for (int i = 0; i < MAX_CODE_TRIES; i++) {
	// 		String categoryCode = CategoryCodeGenerator.generateCategoryCode(prefix);
	// 		switch (prefix) {
	// 			case "TC-":
	// 				if (!topCategoryRepository.existsByCategoryCode(categoryCode)) {
	// 					return categoryCode;  // 중복이 없으면 코드 반환
	// 				}
	// 				break;
	// 			case "MC-":
	// 				if (!middleCategoryRepository.existsByCategoryCode(categoryCode)) {
	// 					return categoryCode;  // 중복이 없으면 코드 반환
	// 				}
	// 				break;
	// 			case "BC-":
	// 				if (!bottomCategoryRepository.existsByCategoryCode(categoryCode)) {
	// 					return categoryCode;  // 중복이 없으면 코드 반환
	// 				}
	// 				break;
	// 			default:
	// 				throw new IllegalArgumentException("유효하지 않은 카테고리 코드 접두사입니다: " + prefix);
	// 		}
	//
	// 	}
	// 	throw new IllegalStateException("고유한 카테고리 코드를 생성하는 데 실패했습니다.");
	// }

}

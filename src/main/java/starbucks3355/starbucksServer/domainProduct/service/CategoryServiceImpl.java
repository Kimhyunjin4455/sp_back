package starbucks3355.starbucksServer.domainProduct.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainProduct.dto.requestDto.BottomCategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.requestDto.CategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.requestDto.MiddleCategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.requestDto.TopCategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.repository.BottomCategoryRepository;
import starbucks3355.starbucksServer.domainProduct.repository.CategoryListRepository;
import starbucks3355.starbucksServer.domainProduct.repository.MiddleCategoryRepository;
import starbucks3355.starbucksServer.domainProduct.repository.TopCategoryRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryListRepository categoryListRepository;
	private final TopCategoryRepository topCategoryRepository;
	private final MiddleCategoryRepository middleCategoryRepository;
	private final BottomCategoryRepository bottomCategoryRepository;

	@Override
	public void addCategory(CategoryListRequestDto categorytDto, TopCategoryListRequestDto topCategoryDto,
		MiddleCategoryListRequestDto middleCategoryDto, BottomCategoryListRequestDto bottomCategoryDto) {
		topCategoryRepository.save(topCategoryDto.dtoToEntity());
		middleCategoryRepository.save(middleCategoryDto.dtoToEntity());
		//bottomCategoryRepository.save();
	}
}

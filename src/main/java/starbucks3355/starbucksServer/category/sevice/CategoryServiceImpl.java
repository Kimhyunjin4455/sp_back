package starbucks3355.starbucksServer.category.sevice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.category.dto.request.BottomCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.CategoryListRequestDto;
import starbucks3355.starbucksServer.category.dto.request.MiddleCategoryRequestDto;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryRequestDto;
import starbucks3355.starbucksServer.category.repository.BottomCategoryRepository;
import starbucks3355.starbucksServer.category.repository.CategoryListRepository;
import starbucks3355.starbucksServer.category.repository.MiddleCategoryRepository;
import starbucks3355.starbucksServer.category.repository.TopCategoryRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryListRepository categoryListRepository;
	private final TopCategoryRepository topCategoryRepository;
	private final MiddleCategoryRepository middleCategoryRepository;
	private final BottomCategoryRepository bottomCategoryRepository;

	@Override
	public void addCategory(CategoryListRequestDto categorytDto, TopCategoryRequestDto topCategoryDto,
		MiddleCategoryRequestDto middleCategoryDto, BottomCategoryRequestDto bottomCategoryDto) {
		topCategoryRepository.save(topCategoryDto.dtoToEntity());
		middleCategoryRepository.save(middleCategoryDto.dtoToEntity());
		//bottomCategoryRepository.save();
	}
}

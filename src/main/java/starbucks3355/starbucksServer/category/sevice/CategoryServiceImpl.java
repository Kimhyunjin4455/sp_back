package starbucks3355.starbucksServer.category.sevice;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
<<<<<<< Updated upstream:src/main/java/starbucks3355/starbucksServer/category/sevice/CategoryServiceImpl.java
import starbucks3355.starbucksServer.category.dto.request.BottomCategoryListRequestDto;
import starbucks3355.starbucksServer.category.dto.request.CategoryListRequestDto;
import starbucks3355.starbucksServer.category.dto.request.MiddleCategoryListRequestDto;
import starbucks3355.starbucksServer.category.dto.request.TopCategoryListRequestDto;
import starbucks3355.starbucksServer.category.repository.BottomCategoryRepository;
import starbucks3355.starbucksServer.category.repository.CategoryListRepository;
import starbucks3355.starbucksServer.category.repository.MiddleCategoryRepository;
import starbucks3355.starbucksServer.category.repository.TopCategoryRepository;
=======
import starbucks3355.starbucksServer.domainProduct.dto.request.BottomCategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.request.CategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.request.MiddleCategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.request.TopCategoryListRequestDto;
import starbucks3355.starbucksServer.domainProduct.repository.BottomCategoryRepository;
import starbucks3355.starbucksServer.domainProduct.repository.CategoryListRepository;
import starbucks3355.starbucksServer.domainProduct.repository.MiddleCategoryRepository;
import starbucks3355.starbucksServer.domainProduct.repository.TopCategoryRepository;
>>>>>>> Stashed changes:src/main/java/starbucks3355/starbucksServer/domainProduct/service/CategoryServiceImpl.java

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

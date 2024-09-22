package starbucks3355.starbucksServer.vendor.repository;

import static starbucks3355.starbucksServer.domainProduct.entity.QProduct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.category.entity.CategoryList;
import starbucks3355.starbucksServer.category.entity.QCategoryList;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.vendor.dto.out.CategoryAndCountOfSearchedProductListResponseDto;

@RequiredArgsConstructor
@Repository
public class ProductListByCategoryRepositoryCustomImpl implements ProductListByCategoryRepositoryCustom {
	private static final int DEFAULT_PAGE_SIZE = 20;
	private static final int DEFAULT_PAGE_NUMBER = 0;

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public CursorPage<String> getProductsByCategoryList(
		String topCategoryName,
		String middleCategoryName,
		Long lastId,
		Integer pageSize,
		Integer page
	) {

		QCategoryList qCategoryList = QCategoryList.categoryList;
		BooleanBuilder builder = new BooleanBuilder();

		Optional.ofNullable(topCategoryName)
			.ifPresent(name -> builder.and(qCategoryList.topCategoryName.eq(name)));

		Optional.ofNullable(middleCategoryName)
			.ifPresent(name -> builder.and(qCategoryList.middleCategoryName.eq(name)));

		Optional.ofNullable(lastId)
			.ifPresent(id -> builder.and(qCategoryList.id.lt(id)));

		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

		// 같은 카테고리의 상품 목록 조회
		List<CategoryList> content = jpaQueryFactory
			.select(qCategoryList)
			.from(qCategoryList)
			.where(builder)
			.orderBy(qCategoryList.id.desc())
			.offset(offset)
			.limit(currentPageSize)
			.fetch();

		Long nextCursor = null;
		boolean hasNext = false;

		if (content.size() > currentPageSize) {
			hasNext = true;
			content = content.subList(0, currentPageSize);
			nextCursor = content.get(content.size() - 1).getId();
		}

		List<String> productUuid = content.stream()
			.map(CategoryList::getProductUuid)
			.toList();
		return new CursorPage<>(productUuid, nextCursor, hasNext, currentPageSize, currentPage);
	}

	@Override
	public List<CategoryAndCountOfSearchedProductListResponseDto> getCategoryAndCountOfSearchedProductList(
		List<String> productUuidList) {
		QCategoryList categoryList = QCategoryList.categoryList;

		// 결과를 저장할 리스트
		List<CategoryAndCountOfSearchedProductListResponseDto> result = new ArrayList<>();

		// 상품 UUID를 이용하여 카테고리와 상품 수 조회
		List<Tuple> categoryCounts = jpaQueryFactory
			.select(categoryList.topCategoryName, product.count())
			.from(product)
			.leftJoin(categoryList).on(product.productUuid.eq(categoryList.productUuid))// 상품과 카테고리 조인
			.where(product.productUuid.in(productUuidList)) // 주어진 UUID 리스트로 필터링
			.groupBy(categoryList.topCategoryName) // 카테고리별로 그룹화
			.fetch();

		// 결과를 DTO 리스트로 변환
		for (Tuple tuple : categoryCounts) {
			String categoryName = tuple.get(categoryList.topCategoryName);
			Long count = tuple.get(1, Long.class); // count() 결과를 Long으로 가져옴

			result.add(CategoryAndCountOfSearchedProductListResponseDto.builder()
				.topCategoryName(categoryName)
				.count(count.intValue()) // Integer로 변환
				.build());
		}

		return result;

	}

	@Override
	public CursorPage<String> getProductByCategoryOfSearchedProducts(
		List<String> productUuidList,
		String topCategoryName,
		Long lastId,
		Integer pageSize,
		Integer page
	) {
		QCategoryList categoryList = QCategoryList.categoryList;
		BooleanBuilder builder = new BooleanBuilder();

		Optional.ofNullable(topCategoryName)
			.ifPresent(name -> builder.and(categoryList.topCategoryName.eq(name)));

		Optional.ofNullable(lastId)
			.ifPresent(id -> builder.and(categoryList.id.lt(id)));

		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

		// 상품 UUID 리스트로 카테고리 목록 조회
		List<CategoryList> content = jpaQueryFactory
			.select(categoryList)
			.from(categoryList)
			.where(categoryList.productUuid.in(productUuidList))
			.where(builder)
			.orderBy(categoryList.id.desc())
			.offset(offset)
			.limit(currentPageSize + 1) // 다음 페이지 확인을 위해 pageSize + 1로 가져옴
			.fetch();

		Long nextCursor = null;
		boolean hasNext = false;

		if (content.size() > currentPageSize) {
			hasNext = true;
			content = content.subList(0, currentPageSize);
			nextCursor = content.get(content.size() - 1).getId();
		}

		List<String> productCodes = content.stream()
			.map(CategoryList::getProductUuid)
			.toList();

		return new CursorPage<>(productCodes, nextCursor, hasNext, currentPageSize, currentPage);

	}

}

package starbucks3355.starbucksServer.vendor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.category.entity.CategoryList;
import starbucks3355.starbucksServer.category.entity.QCategoryList;
import starbucks3355.starbucksServer.common.utils.CursorPage;

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
}

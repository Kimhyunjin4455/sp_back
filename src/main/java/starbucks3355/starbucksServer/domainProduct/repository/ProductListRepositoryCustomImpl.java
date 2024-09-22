package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.entity.QProduct;
import starbucks3355.starbucksServer.domainProduct.entity.QProductTag;

@RequiredArgsConstructor
@Repository
public class ProductListRepositoryCustomImpl implements ProductListRepositoryCustom {
	private static final int DEFAULT_PAGE_SIZE = 20;
	private static final int DEFAULT_PAGE_NUMBER = 0;

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public CursorPage<String> getProductList(
		Long lastId,
		Integer pageSize,
		Integer page) {
		//상품목록 조회
		QProduct product = QProduct.product;
		BooleanBuilder builder = new BooleanBuilder();

		// 마지막 ID 커서 적용
		Optional.ofNullable(lastId)
			.ifPresent(id -> builder.and(product.id.lt(id)));

		// 페이지와 페이지 크기 기본값 설정
		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		// offset 계산
		int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

		// 데이터 페칭 (pageSize + 1로 가져와서 다음 페이지 확인)
		List<Product> content = jpaQueryFactory
			.selectFrom(product)
			.where(builder)
			.orderBy(product.id.desc())
			.offset(offset)
			.limit(currentPageSize + 1)  // 다음 페이지 확인을 위해 pageSize + 1로 가져옴
			.fetch();

		// 다음 페이지의 커서 처리 및 hasNext 여부 판단
		Long nextCursor = null;
		boolean hasNext = false;

		if (content.size() > currentPageSize) {
			hasNext = true;
			content = content.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
			nextCursor = content.get(content.size() - 1).getId();  // 마지막 항목의 ID를 커서로 설정, lastId의 다음 값이 nextCursor
		}

		// productCode 리스트로 변환
		List<String> productCodes = content.stream()
			.map(Product::getProductUuid)
			.toList();

		// CursorPage 객체 반환
		return new CursorPage<>(productCodes, nextCursor, hasNext, currentPageSize, currentPage);
	}

	@Override
	public CursorPage<String> getSearchedProductList(
		String keyword,
		Long lastId,
		Integer pageSize,
		Integer page
	) {
		QProduct product = QProduct.product;
		QProductTag productTag = QProductTag.productTag;
		//QCategoryList categoryList = QCategoryList.categoryList;
		//QPromotion promotion = QPromotion.promotion;
		BooleanBuilder builder = new BooleanBuilder();

		// keyword 의 첫글자의 # 이 붙어있으면 Tag에 속한 상품을 검색
		// # 이 붙어있지 않으면 상품명에 포함된 상품을 검색

		// 키워드에 따른 검색 조건 설정
		if (keyword != null) {
			if (keyword.startsWith("#")) {
				// 태그 검색
				String tagName = keyword.substring(1); // '#' 제거
				Optional.ofNullable(tagName)
					.ifPresent(tag -> builder.and(productTag.tagName.eq(tag))); // 제품 태그로 필터링
			} else {
				// 상품명 검색
				Optional.ofNullable(keyword)
					.ifPresent(word -> builder.and(product.productName.containsIgnoreCase(word))); // 상품명에 키워드 포함
			}
		}

		// 마지막 ID 커서 적용
		Optional.ofNullable(lastId)
			.ifPresent(id -> builder.and(product.id.lt(id))); // lastId보다 작은 ID만 조회

		// 페이지와 페이지 크기 기본값 설정
		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		// offset 계산
		int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

		// 데이터 페칭 (pageSize + 1로 가져와서 다음 페이지 확인)
		List<Product> content = jpaQueryFactory
			.selectFrom(product)
			.leftJoin(productTag).on(product.productUuid.eq(productTag.productUuid)) // 태그와 조인
			.where(builder)
			.orderBy(product.id.desc()) // ID 기준 내림차순 정렬
			.offset(offset)
			.limit(currentPageSize + 1) // 다음 페이지 확인을 위해 pageSize + 1로 가져옴
			.fetch();

		// 다음 페이지의 커서 처리 및 hasNext 여부 판단
		Long nextCursor = null;
		boolean hasNext = false;

		if (content.size() > currentPageSize) {
			hasNext = true;
			content = content.subList(0, currentPageSize); // 실제 페이지 사이즈 만큼 자르기
			nextCursor = content.get(content.size() - 1).getId(); // 마지막 항목의 ID를 커서로 설정
		}

		// productCode 리스트로 변환
		List<String> productCodes = content.stream()
			.map(Product::getProductUuid)
			.toList();

		// CursorPage 객체 반환
		return new CursorPage<>(productCodes, nextCursor, hasNext, currentPageSize, currentPage);

	}
	// 이 상품 목록에 대해 그 상품들이 속한 카테고리와 몇개가 그 카테고리에 속해있는지 반환
	// 이 상품 목록에 대해 그 상품들이 속한 프로모션을 반환
	// 현재 상품목록의 uuid 를 넘겨서 카테고리명과 프로모션명에 대해서 필터링 적용하는 메소드

}

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
}

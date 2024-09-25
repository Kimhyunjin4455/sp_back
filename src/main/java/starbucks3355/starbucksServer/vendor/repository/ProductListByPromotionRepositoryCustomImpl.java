package starbucks3355.starbucksServer.vendor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.vendor.entity.ProductByPromotionList;
import starbucks3355.starbucksServer.vendor.entity.QProductByPromotionList;

@RequiredArgsConstructor
@Repository
public class ProductListByPromotionRepositoryCustomImpl implements ProductListByPromotionRepositoryCustom {
	private static final int DEFAULT_PAGE_SIZE = 20;
	private static final int DEFAULT_PAGE_NUMBER = 0;
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public CursorPage<String> getProductByPromotionList(
		String promotionUuid,
		Long lastId,
		Integer pageSize,
		Integer page
	) {

		QProductByPromotionList qProductByPromotionList = QProductByPromotionList.productByPromotionList;
		BooleanBuilder builder = new BooleanBuilder();

		Optional.ofNullable(promotionUuid)
			.ifPresent(uuid -> builder.and(qProductByPromotionList.promotionUuid.eq(uuid)));

		Optional.ofNullable(lastId)
			.ifPresent(id -> builder.and(qProductByPromotionList.id.lt(id)));

		// 기획전 uuid를 통해 상품 uuid를 조회
		List<ProductByPromotionList> result = jpaQueryFactory
			.select(qProductByPromotionList)
			.from(qProductByPromotionList)
			.where(builder)
			.fetch();

		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		Long nextCursor = null;
		boolean hasNext = false;

		if (result.size() > currentPageSize) {
			hasNext = true;
			result = result.subList(0, currentPageSize);
			nextCursor = result.get(result.size() - 1).getId();
		}

		List<String> productsByPromotion = result.stream()
			.map(ProductByPromotionList::getProductUuid)
			.toList();

		return new CursorPage<>(productsByPromotion, nextCursor, hasNext, currentPageSize, currentPage);

	}

	// 상품에 대한 연관상품(기획전)
	@Override
	public CursorPage<String> getProductsBySamePromotion(
		String productUuid,
		Long lastId,
		Integer pageSize,
		Integer page
	) {

		QProductByPromotionList qProductByPromotionList = QProductByPromotionList.productByPromotionList;
		BooleanBuilder builder = new BooleanBuilder();

		// 나라면 상품1의 uuid로 기획전 리스트를 뽑을거야

		// 상품 uuid를 통해 기획전 uuid를 조회
		List<String> promotionUuidList = jpaQueryFactory // 여름기획전, 가을기획전
			.select(qProductByPromotionList.promotionUuid)
			.from(qProductByPromotionList)
			.where(qProductByPromotionList.productUuid.eq(productUuid))
			.fetch();

		List<ProductByPromotionList> result = jpaQueryFactory // 여름기획전에 속한 상품들
			.select(qProductByPromotionList)
			.from(qProductByPromotionList)
			//.where(qProductByPromotionList.promotionUuid.eq(promotionUuidList.get(0)))
			.where(qProductByPromotionList.promotionUuid.in(promotionUuidList)) // 중복 데이터 조회
			.groupBy(qProductByPromotionList.productUuid)
			.fetch();

		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		Long nextCursor = null;
		boolean hasNext = false;

		if (result.size() > currentPageSize) {
			hasNext = true;
			result = result.subList(0, currentPageSize);
			nextCursor = result.get(result.size() - 1).getId();
		}

		// 현재 상품의 uuid를 제외한 상품 목록을 조회
		List<String> productsByPromotion = result.stream()
			.map(ProductByPromotionList::getProductUuid)
			.toList();

		return new CursorPage<>(productsByPromotion, nextCursor, hasNext, currentPageSize, currentPage);

	}
}

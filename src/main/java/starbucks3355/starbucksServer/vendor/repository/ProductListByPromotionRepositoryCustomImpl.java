package starbucks3355.starbucksServer.vendor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainImage.repository.ImageRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductDetailsRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductRepository;
import starbucks3355.starbucksServer.vendor.entity.ProductByPromotionList;
import starbucks3355.starbucksServer.vendor.entity.QProductByPromotionList;

@RequiredArgsConstructor
@Repository
public class ProductListByPromotionRepositoryCustomImpl implements ProductListByPromotionRepositoryCustom {
	private static final int DEFAULT_PAGE_SIZE = 20;
	private static final int DEFAULT_PAGE_NUMBER = 0;
	private final JPAQueryFactory jpaQueryFactory;

	private final ProductRepository productRepository;
	private final ProductDetailsRepository productDetailsRepository;
	private final ImageRepository imageRepository;

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

		// if (promotionUuid == null) {
		// 	throw new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT);
		// }

		Optional.ofNullable(lastId)
			.ifPresent(id -> builder.and(qProductByPromotionList.id.lt(id)));

		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		// 기획전 uuid를 통해 상품 uuid를 조회
		List<ProductByPromotionList> result = jpaQueryFactory
			.select(qProductByPromotionList)
			.from(qProductByPromotionList)
			.where(builder)
			.orderBy(qProductByPromotionList.id.desc())
			.limit(currentPageSize + 1) // 다음 페이지가 있는지 확인하기 위해 +1
			.fetch();

		// if (result.isEmpty()) {
		// 	return new CursorPage<>(List.of(), null, false, 0, 0);
		// }

		// result가 null 인 경우 예외 발생
		// if (result.isEmpty()) {
		// 	throw new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT);
		// } // 0240

		if (result.isEmpty()) {
			return new CursorPage<>(List.of(), null, false, 0, 0);
		}

		Long nextCursor = null;
		boolean hasNext = false;

		// currentPageSize가 0인 경우 처리
		if (result.size() > currentPageSize) {
			hasNext = true;
			result = result.subList(0, currentPageSize);
			nextCursor = result.get(result.size() - 1).getId();
		}

		List<String> productsByPromotion = result.stream()
			.map(ProductByPromotionList::getProductUuid)
			.toList();

		// // 상품 목록 한 번에 조회
		// List<Product> products = productRepository.findByProductUuidIn(productsByPromotion);
		// List<ProductDetails> productDetails = productDetailsRepository.findByProductUuidIn(productsByPromotion);
		// List<Image> images = imageRepository.findByOtherUuidIn(productsByPromotion);

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


		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		// 상품 uuid를 통해 기획전 uuid를 조회
		List<String> promotionUuidList = jpaQueryFactory // 여름기획전, 가을기획전
			.select(qProductByPromotionList.promotionUuid)
			.from(qProductByPromotionList)
			.where(qProductByPromotionList.productUuid.eq(productUuid))
			.orderBy(qProductByPromotionList.id.desc())
			.limit(currentPageSize + 1)
			.fetch();

		List<ProductByPromotionList> result = jpaQueryFactory // 여름기획전에 속한 상품들
			.select(qProductByPromotionList)
			.from(qProductByPromotionList)
			//.where(qProductByPromotionList.promotionUuid.eq(promotionUuidList.get(0)))
			.where(qProductByPromotionList.promotionUuid.in(promotionUuidList)) // 중복 데이터 조회
			.groupBy(qProductByPromotionList.productUuid)
			.fetch();

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

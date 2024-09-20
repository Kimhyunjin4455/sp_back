package starbucks3355.starbucksServer.domainPromotion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionNameResponseDto;
import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionProductResponseDto;
import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionResponseDto;
import starbucks3355.starbucksServer.domainPromotion.entity.QPromotion;

@RequiredArgsConstructor
@Repository
public class PromotionRepositoryCustomImpl implements PromotionRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<PromotionResponseDto> getPromotionUuidList() {

		QPromotion promotion = QPromotion.promotion;
		BooleanBuilder builder = new BooleanBuilder();

		List<String> promotionUuidList = jpaQueryFactory
			.select(promotion.promotionUuid)
			.from(promotion)
			.fetch();

		return promotionUuidList.stream()
			.map(PromotionResponseDto::new)
			.toList();
	}

	@Override
	public PromotionNameResponseDto getPromotionName(String promotionUuid) {
		QPromotion promotion = QPromotion.promotion;
		BooleanBuilder builder = new BooleanBuilder();

		// 기획전의 uuid가 같으면 기획전 이름을 조회
		String promotionName = jpaQueryFactory
			.select(promotion.promotionName)
			.from(promotion)
			.where(promotion.promotionUuid.eq(promotionUuid))
			.fetchOne();

		return promotionName == null ? null : new PromotionNameResponseDto(promotionName);
	}

	@Override
	public List<PromotionProductResponseDto> getPromotionProductList(String promotionUuid) {
		QPromotion promotion = QPromotion.promotion;
		BooleanBuilder builder = new BooleanBuilder();

		// 기획전의 uuid가 같으면 기획전 상품 목록을 조회
		List<String> productsList = jpaQueryFactory
			.select(promotion.productUuid)
			.from(promotion)
			.where(promotion.promotionUuid.eq(promotionUuid))
			.fetch();

		return productsList.stream()
			.map(PromotionProductResponseDto::new)
			.toList();
	}
}

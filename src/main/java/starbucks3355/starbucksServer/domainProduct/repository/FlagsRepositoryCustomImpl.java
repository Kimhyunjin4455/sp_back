package starbucks3355.starbucksServer.domainProduct.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainProduct.entity.QProduct;

@Repository
@RequiredArgsConstructor
public class FlagsRepositoryCustomImpl implements FlagsRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public void saveFlags(String productUuid) {
		// isBest: 상품이 베스트 기준을 만족하는 상품이면 true
		// isNew: 상품의 uuid을 통해 상품의 등록시간이 최신 10개에 속하면 true 로 설정하는 메서드
		QProduct qProduct = QProduct.product;
		BooleanBuilder builder = new BooleanBuilder();

		// 상품의 등록시간이 최신 10개에 속하면 true 로 설정
		boolean isNew = queryFactory
			.select(qProduct.productUuid)
			.from(qProduct)
			.orderBy(qProduct.regDate.desc())
			.limit(10)
			.fetch()
			.contains(productUuid);

	}
}

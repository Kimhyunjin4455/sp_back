package starbucks3355.starbucksServer.domainReview.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewScoreResponseDto;
import starbucks3355.starbucksServer.domainReview.entity.QReview;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public ReviewScoreResponseDto getReviewScore(String productUuid) {
		QReview review = QReview.review;
		BooleanBuilder builder = new BooleanBuilder();

		// 상품의 uuid에 대해 리뷰 점수의 평균 조회
		String reviewScore = jpaQueryFactory
			.select(review.reviewScore.avg())
			.from(review)
			.where(review.productUuid.eq(productUuid))
			.fetchOne().toString();

		// 상품의 uuid에 대해 리뷰의 개수 조회
		String reviewCount = jpaQueryFactory
			.select(review.reviewUuid.count())
			.from(review)
			.where(review.productUuid.eq(productUuid))
			.fetchOne().toString();

		return reviewScore == null ? null : new ReviewScoreResponseDto(reviewScore, reviewCount);
	}
}

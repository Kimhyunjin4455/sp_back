package starbucks3355.starbucksServer.domainReview.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainImage.entity.QImage;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewScoreResponseDto;
import starbucks3355.starbucksServer.domainReview.entity.QReview;
import starbucks3355.starbucksServer.domainReview.entity.QReviewAggregate;
import starbucks3355.starbucksServer.domainReview.entity.Review;
import starbucks3355.starbucksServer.domainReview.entity.ReviewAggregate;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

	private static final int DEFAULT_PAGE_SIZE = 20;
	private static final int DEFAULT_PAGE_NUMBER = 0;
	private final JPAQueryFactory jpaQueryFactory;
	private final ReviewAggregateRepository reviewAggregateRepository;

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

	@Override
	public CursorPage<String> getProductReviewsHaveMedia(
		String productUuid,
		Long lastId,
		Integer pageSize,
		Integer page
	) {

		QReview review = QReview.review;
		QImage image = QImage.image;
		BooleanBuilder builder = new BooleanBuilder();

		// 마지막 ID 커서 적용
		Optional.ofNullable(lastId)
			.ifPresent(id -> builder.and(review.id.lt(id)));

		// 페이지와 페이지 크기 기본값 설정
		int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
		int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);

		// offset 계산
		int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;
		// 최신 순 부터 정렬해서 보여주는 정책이기에 (currentPage - 1) * currentPageSize 을 offset(쿼리 결과의 시작 위치를 지정)으로 사용

		List<Review> mediaReviews = jpaQueryFactory
			.select(review)
			.from(review)
			.join(image)
			.on(review.reviewUuid.eq(image.otherUuid))
			.where(review.productUuid.eq(productUuid)
				.and(builder))
			.orderBy(review.reviewUuid.asc())
			.limit(currentPageSize + 1)
			.offset(offset)
			.fetch();

		// 다음 페이지의 커서 처리 및 hasNext 여부 판단
		Long nextCursor = null;
		boolean hasNext = false;

		if (mediaReviews.size() > currentPageSize) {
			hasNext = true;
			mediaReviews = mediaReviews.subList(0, currentPageSize);  // 실제 페이지 사이즈 만큼 자르기
			nextCursor = mediaReviews.get(mediaReviews.size() - 1).getId();  // 마지막 항목의 ID를 커서로 설정
		}

		List<String> mediaReviewList = mediaReviews.stream()
			.map(Review::getReviewUuid)
			.toList();

		return new CursorPage<>(mediaReviewList, nextCursor, hasNext, currentPageSize, currentPage);

	}

	@Override
	public ReviewResponseDto getReviewAndUpdateAggregate(String reviewUuid) {
		QReview review = QReview.review;

		Review reviewEntity = jpaQueryFactory
			.selectFrom(review)
			.where(review.reviewUuid.eq(reviewUuid))
			.fetchOne();

		updateReviewAggregate(reviewEntity);

		return new ReviewResponseDto(
			reviewEntity.getReviewUuid(),
			reviewEntity.getReviewScore(),
			reviewEntity.getRegDate(),
			reviewEntity.getModDate()
		);
	}

	@Override
	public void updateReviewAggregate(Review review) {
		if (review == null) {
			return;
		}

		QReviewAggregate reviewAggregate = QReviewAggregate.reviewAggregate;

		ReviewAggregate aggregate = jpaQueryFactory
			.selectFrom(reviewAggregate)
			.where(reviewAggregate.reviewUuid.eq(review.getReviewUuid()))
			.fetchOne();

		if (aggregate != null) {
			ReviewAggregate updateAggregate = aggregate.incrementViewCount();
			reviewAggregateRepository.save(updateAggregate);
		} else {
			ReviewAggregate newAggregate = ReviewAggregate.builder()
				.reviewUuid(review.getReviewUuid())
				.viewCount(1)
				.reviewScore(review.getReviewScore())
				.build();
			reviewAggregateRepository.save(newAggregate);
		}

		// 리뷰 집계 테이블이 없으면 생성

	}

	@Override
	public CursorPage<String> getBestReviews(Long lastId, Integer pageSize, Integer page) {
		QReview review = QReview.review;
		QReviewAggregate reviewAggregate = QReviewAggregate.reviewAggregate;

		return null;
	}
}

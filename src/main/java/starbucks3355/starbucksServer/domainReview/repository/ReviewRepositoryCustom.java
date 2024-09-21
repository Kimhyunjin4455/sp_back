package starbucks3355.starbucksServer.domainReview.repository;

import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewScoreResponseDto;
import starbucks3355.starbucksServer.domainReview.entity.Review;

@Repository
public interface ReviewRepositoryCustom {
	ReviewScoreResponseDto getReviewScore(String productUuid);

	CursorPage<String> getProductReviewsHaveMedia(
		String productUuid,
		Long lastId,
		Integer pageSize,
		Integer page
	);

	ReviewResponseDto getReviewAndUpdateAggregate(String reviewUuid);

	void updateReviewAggregate(Review review);

	CursorPage<String> getBestReviews(
		Long lastId,
		Integer pageSize,
		Integer page
	);

}

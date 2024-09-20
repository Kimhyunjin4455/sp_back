package starbucks3355.starbucksServer.domainReview.repository;

import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainReview.dto.out.ReviewScoreResponseDto;

@Repository
public interface ReviewRepositoryCustom {
	ReviewScoreResponseDto getReviewScore(String productUuid);
}

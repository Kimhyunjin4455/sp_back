package starbucks3355.starbucksServer.domainReview.service;

import java.util.List;

import org.springframework.data.domain.Slice;

import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewModifyRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.out.BestReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewProductResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewScoreResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.UserReviewResponseDto;

public interface ReviewService {
	public List<UserReviewResponseDto> getUserReviews(String memberUuid);

	public Slice<ReviewProductResponseDto> getProductReviews(String productUuid, int page, int size);

	CursorPage<String> getProductReviewsHaveMedia(
		String productUuid,
		Long lastId,
		Integer pageSize,
		Integer page
	);

	public ReviewResponseDto getReview(String reviewUuid);

	public ReviewScoreResponseDto getReviewScore(String productUuid);

	//public List<ReviewResponseDto> getBestReviews(String productUuid);

	CursorPage<BestReviewResponseDto> getBestReviews(
		Long lastId,
		Integer pageSize,
		Integer page
	);

	void addReview(ReviewRequestDto reviewRequestDto);

	void addReviewViewCount(String reviewUuid);

	void modifyReview(ReviewModifyRequestDto reviewModifyRequestDto, String reviewUuid);

	void deleteReview(Long reviewId); // 베스트 등을 위해 soft delete로 갈지? -> 엔티티 부터 모든 관련된 곳에 필드 추가 요구됨

}

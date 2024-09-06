package starbucks3355.starbucksServer.domainReview.service;

import java.util.List;

import starbucks3355.starbucksServer.domainReview.dto.in.ReviewRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.out.MyReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ProductReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;

public interface ReviewService {
	public List<MyReviewResponseDto> getMyReviews(String memberUuid);

	public List<ProductReviewResponseDto> getProductReviews(String productUuid);

	public ReviewResponseDto getReview(String reviewUuid);

	void addReview(ReviewRequestDto reviewRequestDto);

	void modifyReview(ReviewRequestDto reviewRequestDto);

	void deleteReview(Long reviewId); // 베스트 등을 위해 soft delete로 갈지? -> 엔티티 부터 모든 관련된 곳에 필드 추가 요구됨

}

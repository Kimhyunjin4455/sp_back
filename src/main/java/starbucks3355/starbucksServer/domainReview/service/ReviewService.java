package starbucks3355.starbucksServer.domainReview.service;

import java.util.List;

import starbucks3355.starbucksServer.domainReview.dto.out.MyReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ProductReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;

public interface ReviewService {
	public List<MyReviewResponseDto> getMyReviews(String memberUuid);

	public List<ProductReviewResponseDto> getProductReviews(String productUuid);

	public ReviewResponseDto getReview(String reviewUuid);

}

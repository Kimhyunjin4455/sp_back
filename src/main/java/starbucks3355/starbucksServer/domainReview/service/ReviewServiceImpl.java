package starbucks3355.starbucksServer.domainReview.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainReview.dto.out.MyReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ProductReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.entity.Review;
import starbucks3355.starbucksServer.domainReview.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewRepository reviewRepository;

	@Override
	public List<MyReviewResponseDto> getMyReviews(String memberUuid) {
		List<Review> myReviews = reviewRepository.findByMemberUuid(memberUuid);

		if (myReviews != null) {
			return myReviews.stream()
				.map(myReview -> MyReviewResponseDto.builder()
					.content(myReview.getContent())
					.reviewUuid(myReview.getReviewUuid())
					.reviewScore(myReview.getReivewScore())
					.productUuid(myReview.getMemberUuid())
					.memberUuid(myReview.getMemberUuid())
					.regDate(myReview.getRegDate())
					.modDate(myReview.getModDate())
					.build()
				).toList();
		}

		return List.of();

	}

	@Override
	public List<ProductReviewResponseDto> getProductReviews(String productUuid) {
		List<Review> productReviews = reviewRepository.findByProductUuid(productUuid);

		if (productReviews != null) {
			return productReviews.stream()
				.map(productReview -> ProductReviewResponseDto.builder()
					.content(productReview.getContent())
					.reviewScore(productReview.getReivewScore())
					.reviewUuid(productReview.getReviewUuid())
					.productUuid(productReview.getProductUuid())
					.regDate(productReview.getRegDate())
					.modDate(productReview.getModDate())
					.build()
				).toList();
		}
		return List.of();
	}

	@Override
	public ReviewResponseDto getReview(String reviewUuid) {
		Review review = reviewRepository.findByReviewUuid(reviewUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 없습니다."));

		return ReviewResponseDto.builder()
			.content(review.getContent())
			.reivewScore(review.getReivewScore())
			.regDate(review.getRegDate())
			.modDate(review.getModDate())
			.build();
	}
}

package starbucks3355.starbucksServer.domainReview.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewModifyRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.out.BestReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewScoreResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.UserReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.entity.Review;
import starbucks3355.starbucksServer.domainReview.repository.ReviewAggregateRepository;
import starbucks3355.starbucksServer.domainReview.repository.ReviewRepository;
import starbucks3355.starbucksServer.domainReview.repository.ReviewRepositoryCustom;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewRepository reviewRepository;
	private final ReviewRepositoryCustom reviewRepositoryCustom;
	private final ReviewAggregateRepository reviewAggregateRepository;

	@Override
	public List<UserReviewResponseDto> getUserReviews(String authorName) {
		List<Review> myReviews = reviewRepository.findByAuthorName(authorName);

		if (myReviews != null) {
			return myReviews.stream()
				.map(myReview -> UserReviewResponseDto.builder()
					.content(myReview.getContent())
					.reviewUuid(myReview.getReviewUuid())
					.reviewScore(myReview.getReviewScore())
					.productUuid(myReview.getProductUuid())
					.authorName(myReview.getAuthorName())
					.regDate(myReview.getRegDate())
					.modDate(myReview.getModDate())
					.build()
				).toList();
		}// 리뷰 id만

		return List.of();

	}

	@Override
	public CursorPage<String> getProductReviews(
		String productUuid,
		Long lastId,
		Integer pageSize,
		Integer page
	) {
		return reviewRepositoryCustom.getReviewsOfProduct(productUuid, lastId, pageSize, page);

	}

	@Override
	public CursorPage<String> getProductReviewsHaveMedia(
		String productUuid,
		Long lastId,
		Integer pageSize,
		Integer page) {
		return reviewRepositoryCustom.getProductReviewsHaveMedia(productUuid, lastId, pageSize, page);
	}

	@Override
	public ReviewResponseDto getReview(String reviewUuid) {
		Review review = reviewRepository.findByReviewUuid(reviewUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_REVIEW));

		reviewRepositoryCustom.updateReviewAggregate(review);

		return ReviewResponseDto.from(review);
	}

	@Override
	public ReviewScoreResponseDto getReviewScore(String productUuid) {
		return reviewRepositoryCustom.getReviewScore(productUuid);
	}

	@Override
	public CursorPage<BestReviewResponseDto> getBestReviews(Long lastId, Integer pageSize, Integer page) {
		return reviewRepositoryCustom.getBestReviews(lastId, pageSize, page);
	}

	@Override
	public void addReview(ReviewRequestDto reviewRequestDto) {
		try {
			reviewRepository.save(reviewRequestDto.toEntity());
		} catch (Exception e) {
			throw new BaseException(BaseResponseStatus.FAILED_TO_ADD_REVIEWS);
		}
	}

	@Override
	public void addReviewViewCount(String reviewUuid) {
		Optional<Review> result = reviewRepository.findByReviewUuid(reviewUuid);

		Review review = result.get();

		// review.modifyReviewViewCount(review.getReviewViewCount() + 1);

		// modifyXXX 보단 build()를 통해 새로운 객체를 생성하는 것이 좋을 것 같다.

		reviewRepository.save(review);
	}

	@Override
	@Transactional
	public void modifyReview(ReviewModifyRequestDto reviewModifyRequestDto, String reviewUuid) {
		// String reviewUuid = UUID.randomUUID().toString();
		Optional<Review> result = reviewRepository.findByReviewUuid(reviewUuid);

		Review review = result.get();

		review.modifyContent(reviewModifyRequestDto.getContent());
		review.modifyReviewScore(reviewModifyRequestDto.getReviewScore());

		reviewRepository.save(review);

	}

	@Override
	@Transactional
	public void deleteReview( // 소프트delete 형식 추천
		Long reviewId
	) {
		reviewRepository.deleteById(reviewId);
	}
}

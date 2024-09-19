package starbucks3355.starbucksServer.domainReview.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainImage.repository.ImageRepository;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewModifyRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewProductResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.UserReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.entity.Review;
import starbucks3355.starbucksServer.domainReview.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewRepository reviewRepository;
	private final MemberRepository memberRepository;
	private final ImageRepository imageRepository;

	@Override
	public List<UserReviewResponseDto> getUserReviews(String memberUuid) {
		List<Review> myReviews = reviewRepository.findByMemberUuid(memberUuid);

		if (myReviews != null) {
			return myReviews.stream()
				.map(myReview -> UserReviewResponseDto.builder()
					.content(myReview.getContent())
					.reviewUuid(myReview.getReviewUuid())
					.reviewScore(myReview.getReviewScore())
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
	public Slice<ReviewProductResponseDto> getProductReviews(String productUuid, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Slice<Review> productReviews = reviewRepository.findPageByProductUuid(productUuid, pageable);

		return productReviews.map(review -> ReviewProductResponseDto.builder()
			.content(review.getContent())
			.reviewScore(review.getReviewScore())
			.reviewUuid(review.getReviewUuid())
			.productUuid(review.getProductUuid())
			.userId(memberRepository.findByUuid(review.getMemberUuid())
				.map(member -> member.getUserId().substring(0, 3) + "*******")
				.orElse("UNKNOWN")) // 회원 정보가 없을 경우 처리
			.regDate(review.getRegDate())
			.modDate(review.getModDate())
			.build());
	}

	@Override
	public List<ReviewProductResponseDto> getProductReviewsHaveMedia(String productUuid) {
		List<Review> productReviews = reviewRepository.findByProductUuid(productUuid);

		if (productReviews != null) {
			// 상품의 리뷰들에 대해 리뷰에 대한 이미지가 한개라도 있으면 리뷰들을 반환
			return productReviews.stream()
				.filter(productReview -> imageRepository.findByOtherUuid(productReview.getReviewUuid()).size() > 0)
				.map(productReview -> ReviewProductResponseDto.builder()
					.content(productReview.getContent())
					.reviewScore(productReview.getReviewScore())
					.reviewUuid(productReview.getReviewUuid())
					.productUuid(productReview.getProductUuid())
					.userId(memberRepository.findByUuid(productReview.getMemberUuid())
						.get()
						.getUserId()
						.substring(0, 3) + "*******")
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
			.reivewScore(review.getReviewScore())
			.regDate(review.getRegDate())
			.modDate(review.getModDate())
			.build();
	}

	@Override
	public List<ReviewResponseDto> getBestReviews(String productUuid) {
		// 상품의 리뷰들 중 평점과 조회수 높은 리뷰들을 5개까지 반환

		List<Review> productReviews = reviewRepository.findTop5ByProductUuidOrderByReviewScoreDescReviewViewCountDesc(
			productUuid);

		if (productReviews != null) {
			return productReviews.stream()
				.map(productReview -> ReviewResponseDto.builder()
					.content(productReview.getContent())
					.reivewScore(productReview.getReviewScore())
					.regDate(productReview.getRegDate())
					.modDate(productReview.getModDate())
					.build()
				).toList();
		}

		return List.of();
	}

	@Override
	public void addReview(ReviewRequestDto reviewRequestDto) {
		//reviewUuid가 존재하면 추가하지 않고 예외 발생
		if (reviewRepository.existsByReviewUuid(reviewRequestDto.getReviewUuid())) {
			throw new IllegalArgumentException("리뷰 UUID가 이미 존재합니다: " + reviewRequestDto.getReviewUuid());
		}

		reviewRepository.save(reviewRequestDto.toEntity(reviewRequestDto.getReviewUuid(),
			reviewRequestDto.getProductUuid(), reviewRequestDto.getMemberUuid()));
	}

	@Override
	public void addReviewViewCount(String reviewUuid) {
		Optional<Review> result = reviewRepository.findByReviewUuid(reviewUuid);

		Review review = result.get();

		review.modifyReviewViewCount(review.getReviewViewCount() + 1);

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
	public void deleteReview(
		Long reviewId
	) {
		reviewRepository.deleteById(reviewId);
	}
}

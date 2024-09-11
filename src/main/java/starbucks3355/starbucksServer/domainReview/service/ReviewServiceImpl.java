package starbucks3355.starbucksServer.domainReview.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainImage.repository.ImageRepository;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.out.MyReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ProductReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.entity.Review;
import starbucks3355.starbucksServer.domainReview.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewRepository reviewRepository;
	private final MemberRepository memberRepository;
	private final ImageRepository imageRepository;

	@Override
	public List<MyReviewResponseDto> getMyReviews(String memberUuid) {
		List<Review> myReviews = reviewRepository.findByMemberUuid(memberUuid);

		if (myReviews != null) {
			return myReviews.stream()
				.map(myReview -> MyReviewResponseDto.builder()
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
	public Slice<ProductReviewResponseDto> getProductReviews(String productUuid, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Slice<Review> productReviews = reviewRepository.findPageByProductUuid(productUuid, pageable);

		return productReviews.map(review -> ProductReviewResponseDto.builder()
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
	public List<ProductReviewResponseDto> getProductReviewsHaveMedia(String productUuid) {
		List<Review> productReviews = reviewRepository.findByProductUuid(productUuid);

		if (productReviews != null) {
			// 상품의 리뷰들에 대해 리뷰에 대한 이미지가 한개라도 있으면 리뷰들을 반환
			return productReviews.stream()
				.filter(productReview -> imageRepository.findByOtherUuid(productReview.getReviewUuid()).size() > 0)
				.map(productReview -> ProductReviewResponseDto.builder()
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
	public void addReview(ReviewRequestDto reviewRequestDto) {
		String reviewUuid = UUID.randomUUID().toString();
		String productUuid = UUID.randomUUID().toString();
		String memberUuid = UUID.randomUUID().toString();
		// 스웨거랑 별개로 리뷰 등록 시에는 리뷰, 상품, 회원의 UUID를 생성하여 저장
		reviewRepository.save(reviewRequestDto.toEntity(reviewUuid, productUuid, memberUuid));
	}

	@Override
	public void modifyReview(ReviewRequestDto reviewRequestDto, String reviewUuid) {
		// String reviewUuid = UUID.randomUUID().toString();
		Optional<Review> result = reviewRepository.findByReviewUuid(reviewUuid);

		Review review = result.get();

		review.modifyContent(reviewRequestDto.getContent());
		review.modifyReviewScore(reviewRequestDto.getReviewScore());

		reviewRepository.save(review);

	}

	@Override
	public void deleteReview(Long reviewId) {
		reviewRepository.deleteById(reviewId);
	}
}

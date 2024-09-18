package starbucks3355.starbucksServer.domainReview.controller;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.entity.CommonResponseSliceWithScoreEntity;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewModifyRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.out.MyReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewProductResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.service.ReviewService;
import starbucks3355.starbucksServer.domainReview.vo.in.ReviewModifyRequestVo;
import starbucks3355.starbucksServer.domainReview.vo.in.ReviewRequestVo;
import starbucks3355.starbucksServer.domainReview.vo.out.MyReviewResponseVo;
import starbucks3355.starbucksServer.domainReview.vo.out.ReviewProductResponseVo;
import starbucks3355.starbucksServer.domainReview.vo.out.ReviewResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
@Tag(name = "Review", description = "리뷰 API")
public class ReviewController {
	private final ReviewService reviewService;

	@GetMapping("/{productUuid}/allReviewsOfProduct")
	@Operation(summary = "상품별 리뷰 전체 조회")
	public CommonResponseSliceWithScoreEntity<List<ReviewProductResponseVo>> getProductReviews(
		@PathVariable String productUuid,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {
		Slice<ReviewProductResponseDto> productReviewResponseDtoSlice = reviewService.getProductReviews(productUuid,
			page, size);

		Double reviewAvgScore = productReviewResponseDtoSlice.stream()
			.mapToDouble(ReviewProductResponseDto::getReviewScore)
			.average()
			.orElse(0);

		String formattedScore = String.format("%.1f", reviewAvgScore);

		Integer reviewCnt = productReviewResponseDtoSlice.getNumberOfElements();

		List<ReviewProductResponseVo> responseVoList = productReviewResponseDtoSlice.stream()
			.map(ReviewProductResponseDto::dtoToResponseVo)
			.toList();

		return new CommonResponseSliceWithScoreEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			responseVoList,
			Double.parseDouble(formattedScore), // Double 형식으로 변환 (소수점 1자리까지만 표시하기 위해
			reviewCnt,
			productReviewResponseDtoSlice.hasNext()
		);
	}

	@GetMapping("/{productUuid}/allReviewsHaveMediaOfProduct")
	@Operation(summary = "상품별 리뷰 전체 조회(이미지가 있는 리뷰만)")
	public CommonResponseEntity<List<ReviewProductResponseVo>> getProductReviewsHaveMedia(
		@PathVariable String productUuid) {
		List<ReviewProductResponseDto> productReviewResponseDtoList = reviewService.getProductReviewsHaveMedia(
			productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productReviewResponseDtoList.stream()
				.map(ReviewProductResponseDto::dtoToResponseVo)
				.toList()
		);
	}

	@GetMapping("/{productUuid}/bestReviewsOfProduct")
	@Operation(summary = "상품별 베스트 리뷰 조회")
	public CommonResponseEntity<List<ReviewResponseVo>> getBestReviews(
		@PathVariable String productUuid) {
		List<ReviewResponseDto> bestReviewsDto = reviewService.getBestReviews(productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			bestReviewsDto.stream()
				.map(ReviewResponseDto::dtoToResponseVo)
				.toList()
		);
	}

	@GetMapping("/allReviewsOfMember")
	@Operation(summary = "회원별 리뷰 전체 조회")
	public CommonResponseEntity<List<MyReviewResponseVo>> getMemberReviews(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		String memberUuid = authUserDetail.getUuid(); // 로그인된 사용자의 UUID 가져오기

		List<MyReviewResponseDto> memberReviewsDto = reviewService.getMyReviews(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			memberReviewsDto.stream()
				.map(MyReviewResponseDto::dtoToResponseVo)
				.toList()
		);

	}

	@GetMapping("/{reviewUuid}")
	@Operation(summary = "리뷰 한개 조회")
	public CommonResponseEntity<ReviewResponseVo> getReview(
		@PathVariable String reviewUuid
	) {
		ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewUuid);
		reviewService.addReviewViewCount(reviewUuid);

		return new CommonResponseEntity<ReviewResponseVo>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			reviewResponseDto.dtoToResponseVo()
		);
	}

	@PostMapping("/{reviewUuid}")
	@Operation(summary = "리뷰 한개 등록")
	public CommonResponseEntity<Void> addReview(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody ReviewRequestVo reviewRequestVo) { // Service 로직에서 UUID 생성하여 저장하므로 vo에서 관련정보를 뺴거나, 서비스 로직에서 제거하기

		String memberUuid = authUserDetail.getUuid(); // 로그인된 사용자의 UUID 가져오기

		ReviewRequestDto reviewRequestDto = ReviewRequestDto.builder()
			.content(reviewRequestVo.getContent())
			.reviewUuid(reviewRequestVo.getReviewUuid())
			.reviewScore(reviewRequestVo.getReviewScore())
			.productUuid(reviewRequestVo.getProductUuid())
			.memberUuid(memberUuid)
			.regDate(reviewRequestVo.getRegDate())
			.modDate(reviewRequestVo.getModDate())
			.build();

		reviewService.addReview(reviewRequestDto);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null
		);
	}

	@PutMapping("/{reviewUuid}")
	@Operation(summary = "리뷰 수정", description = "기존에 작성된 리뷰를 수정합니다.")
	public CommonResponseEntity<Void> updateReview(
		@PathVariable String reviewUuid,
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody ReviewModifyRequestVo reviewModifyRequestVo) {

		ReviewModifyRequestDto reviewModifyRequestDto = ReviewModifyRequestDto.builder()
			.content(reviewModifyRequestVo.getContent())
			.reviewScore(reviewModifyRequestVo.getReviewScore())
			.build();

		reviewService.modifyReview(reviewModifyRequestDto, reviewUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null
		);

	}

	@DeleteMapping("/reviewDelete/{id}")
	@Operation(summary = "댓글 삭제", description = "작성했던 리뷰를 삭제합니다.")
	public CommonResponseEntity<Void> deleteReview(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable Long id) {

		reviewService.deleteReview(id);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null
		);
	}

}

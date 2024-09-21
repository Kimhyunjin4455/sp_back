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
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.entity.CommonResponseSliceEntity;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewModifyRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.out.BestReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewProductResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewScoreResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.UserReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.service.ReviewService;
import starbucks3355.starbucksServer.domainReview.vo.in.ReviewModifyRequestVo;
import starbucks3355.starbucksServer.domainReview.vo.in.ReviewRequestVo;
import starbucks3355.starbucksServer.domainReview.vo.out.ReviewProductResponseVo;
import starbucks3355.starbucksServer.domainReview.vo.out.ReviewResponseVo;
import starbucks3355.starbucksServer.domainReview.vo.out.ReviewScoreResponseVo;
import starbucks3355.starbucksServer.domainReview.vo.out.UserReviewResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
@Tag(name = "Review", description = "리뷰 API")
public class ReviewController {
	private final ReviewService reviewService;

	@GetMapping("/{productUuid}/allReviewsOfProduct")
	@Operation(summary = "상품별 리뷰 전체 조회")
	public CommonResponseSliceEntity<List<ReviewProductResponseVo>> getProductReviews(
		@PathVariable String productUuid,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {
		Slice<ReviewProductResponseDto> productReviewResponseDtoSlice = reviewService.getProductReviews(productUuid,
			page, size);

		List<ReviewProductResponseVo> responseVoList = productReviewResponseDtoSlice.stream()
			.map(ReviewProductResponseDto::dtoToResponseVo)
			.toList();

		return new CommonResponseSliceEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			responseVoList,
			productReviewResponseDtoSlice.hasNext()
		);
	}

	@GetMapping("/allReviewsHaveMediaOfProduct")
	@Operation(summary = "상품별 리뷰 전체 조회(이미지가 있는 리뷰만)")
	public BaseResponse<CursorPage<String>> getProductReviewsHaveMedia(
		@RequestParam(value = "productUuid") String productUuid,
		@RequestParam(value = "lastId", required = false) Long lastId,
		@RequestParam(value = "pageSize", required = false) Integer pageSize,
		@RequestParam(value = "page", required = false) Integer page) {

		return new BaseResponse<>(
			reviewService.getProductReviewsHaveMedia(productUuid, lastId, pageSize, page)
		);
	}

	@GetMapping("/bestReviews")
	@Operation(summary = "전체 리뷰들 중 베스트 리뷰 조회")
	public BaseResponse<CursorPage<BestReviewResponseDto>> getBestReviews(
		@RequestParam(value = "lastId", required = false) Long lastId,
		@RequestParam(value = "pageSize", required = false) Integer pageSize,
		@RequestParam(value = "page", required = false) Integer page) {

		return new BaseResponse<>(
			reviewService.getBestReviews(lastId, pageSize, page)
		);
	}

	@GetMapping("/allReviewsOfMyPage")
	@Operation(summary = "나의 리뷰 전체 조회")
	public CommonResponseEntity<List<UserReviewResponseVo>> getMemberReviews(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		// 로그인된 사용자의 UUID 가져와서 그 사용자의 닉네임 찾기
		String username = authUserDetail.getUsername();

		// 수정 필요
		List<UserReviewResponseDto> memberReviewsDto = reviewService.getUserReviews(username);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			memberReviewsDto.stream()
				.map(UserReviewResponseDto::dtoToResponseVo)
				.toList()
		);

	}

	@GetMapping("/{authorName}/allReviewsOfUser")
	@Operation(summary = "작성자의 리뷰 전체 조회")
	// 상품상세 페이지에서 이용할 용도(페이지 생성여부에 따라 갈림)
	public CommonResponseEntity<List<UserReviewResponseVo>> getUserReviews(
		@PathVariable String authorName
	) {
		List<UserReviewResponseDto> userReviews = reviewService.getUserReviews(authorName);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			userReviews.stream()
				.map(UserReviewResponseDto::dtoToResponseVo)
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

	@GetMapping("/{productUuid}/reviewScore")
	@Operation(summary = "상품별 리뷰 점수의 평균과 갯수 조회")
	public BaseResponse<ReviewScoreResponseVo> getReviewScore(
		@PathVariable String productUuid
	) {
		ReviewScoreResponseDto reviewScoreResponseDto = reviewService.getReviewScore(productUuid);

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			reviewScoreResponseDto.dtoToResponseVo()
		);
	}

	@PostMapping("/{reviewUuid}")
	@Operation(summary = "리뷰 한개 등록")
	public BaseResponse<Void> addReview(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody ReviewRequestVo reviewRequestVo) { // Service 로직에서 UUID 생성하여 저장하므로 vo에서 관련정보를 뺴거나, 서비스 로직에서 제거하기

		reviewService.addReview(ReviewRequestDto.of(reviewRequestVo));

		return new BaseResponse<>(
			BaseResponseStatus.SUCCESS
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

	@DeleteMapping("/{id}/reviewDelete")
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

package starbucks3355.starbucksServer.domainReview.controller;

import java.util.List;

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
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewModifyRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.out.BestReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewScoreResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.UserReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.service.ReviewService;
import starbucks3355.starbucksServer.domainReview.vo.in.ReviewModifyRequestVo;
import starbucks3355.starbucksServer.domainReview.vo.in.ReviewRequestVo;
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

	@GetMapping("/allReviewsOfProduct")
	@Operation(summary = "상품별 리뷰 전체 조회")
	public BaseResponse<CursorPage<String>> getProductReviews(
		@RequestParam String productUuid,
		@RequestParam(required = false) Long lastId,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		CursorPage<String> reviews = reviewService.getProductReviews(productUuid, lastId, page, size);

		// 리뷰가 없을 경우 다른 응답 메시지 반환
		if (reviews == null || reviews.getContent().isEmpty()) {
			return new BaseResponse<>(
				HttpStatus.NOT_FOUND, // 404 Not Found
				BaseResponseStatus.NO_EXIST_REVIEW.isSuccess(),
				BaseResponseStatus.NO_EXIST_REVIEW.getMessage(),
				BaseResponseStatus.NO_EXIST_REVIEW.getCode(),
				null // 리뷰가 없으므로 null 반환
			);
		}

		// 리뷰가 있을 경우 정상 응답 반환
		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			reviews
		);

	}

	@GetMapping("/allReviewsHaveMediaOfProduct")
	@Operation(summary = "상품별 리뷰 전체 조회(이미지가 있는 리뷰만)")
	public BaseResponse<CursorPage<String>> getProductReviewsHaveMedia(
		@RequestParam(value = "productUuid") String productUuid,
		@RequestParam(value = "lastId", required = false) Long lastId,
		@RequestParam(value = "pageSize", required = false) Integer pageSize,
		@RequestParam(value = "page", required = false) Integer page) {

		CursorPage<String> productReviewsHaveMedia = reviewService.getProductReviewsHaveMedia(productUuid, lastId,
			pageSize, page);

		// 리뷰가 없을 경우 다른 응답 메시지 반환
		if (productReviewsHaveMedia == null || productReviewsHaveMedia.getContent().isEmpty()) {
			return new BaseResponse<>(
				HttpStatus.NOT_FOUND, // 404 Not Found
				BaseResponseStatus.NO_EXIST_REVIEW.isSuccess(),
				BaseResponseStatus.NO_EXIST_REVIEW.getMessage(),
				BaseResponseStatus.NO_EXIST_REVIEW.getCode(),
				null // 리뷰가 없으므로 null 반환
			);
		}

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			productReviewsHaveMedia
		);
	}

	@GetMapping("/bestReviews")
	@Operation(summary = "전체 리뷰들 중 베스트 리뷰 조회")
	public BaseResponse<CursorPage<BestReviewResponseDto>> getBestReviews(
		@RequestParam(value = "lastId", required = false) Long lastId,
		@RequestParam(value = "pageSize", required = false) Integer pageSize,
		@RequestParam(value = "page", required = false) Integer page) {

		CursorPage<BestReviewResponseDto> bestReviews = reviewService.getBestReviews(lastId, pageSize, page);

		// 리뷰가 없을 경우 다른 응답 메시지 반환
		if (bestReviews == null || bestReviews.getContent().isEmpty()) {
			return new BaseResponse<>(
				HttpStatus.NOT_FOUND, // 404 Not Found
				BaseResponseStatus.NO_EXIST_REVIEW.isSuccess(),
				BaseResponseStatus.NO_EXIST_REVIEW.getMessage(),
				BaseResponseStatus.NO_EXIST_REVIEW.getCode(),
				null // 리뷰가 없으므로 null 반환
			);
		}

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			bestReviews
		);
	}

	@GetMapping("/allReviewsOfMyPage")
	@Operation(summary = "나의 리뷰 전체 조회")
	public BaseResponse<List<UserReviewResponseVo>> getMemberReviews(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		if (authUserDetail == null) {
			return new BaseResponse<>(
				HttpStatus.UNAUTHORIZED,
				BaseResponseStatus.TOKEN_NOT_VALID.isSuccess(),
				BaseResponseStatus.TOKEN_NOT_VALID.getMessage(),
				BaseResponseStatus.TOKEN_NOT_VALID.getCode(),
				null
			);
		}

		// 로그인된 사용자의 UUID 가져와서 그 사용자의 닉네임 찾기
		String username = authUserDetail.getNickname();

		// 수정 필요
		List<UserReviewResponseDto> memberReviewsDto = reviewService.getUserReviews(username);

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			memberReviewsDto.stream()
				.map(UserReviewResponseDto::dtoToResponseVo)
				.toList()
		);

	}

	@GetMapping("/{authorName}/allReviewsOfUser")
	@Operation(summary = "작성자의 리뷰 전체 조회")
	// 상품상세 페이지에서 이용할 용도(페이지 생성여부에 따라 갈림)
	public BaseResponse<List<UserReviewResponseVo>> getUserReviews(
		@PathVariable String authorName
	) {
		List<UserReviewResponseDto> userReviews = reviewService.getUserReviews(authorName);

		if (userReviews == null || userReviews.isEmpty()) {
			return new BaseResponse<>(
				HttpStatus.NOT_FOUND,
				BaseResponseStatus.NO_EXIST_REVIEW.isSuccess(),
				BaseResponseStatus.NO_EXIST_REVIEW.getMessage(),
				BaseResponseStatus.NO_EXIST_REVIEW.getCode(),
				null
			);
		}

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			userReviews.stream()
				.map(UserReviewResponseDto::dtoToResponseVo)
				.toList()
		);

	}

	@GetMapping("/{reviewUuid}")
	@Operation(summary = "리뷰 한개 조회")
	public BaseResponse<ReviewResponseVo> getReview(
		@PathVariable String reviewUuid
	) {
		ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewUuid);

		if (reviewResponseDto == null) {
			return new BaseResponse<>(
				HttpStatus.NOT_FOUND,
				BaseResponseStatus.NO_EXIST_REVIEW.isSuccess(),
				BaseResponseStatus.NO_EXIST_REVIEW.getMessage(),
				BaseResponseStatus.NO_EXIST_REVIEW.getCode(),
				null
			);
		}

		reviewService.addReviewViewCount(reviewUuid);

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			reviewResponseDto.dtoToResponseVo()
		);
	}

	@GetMapping("/{productUuid}/reviewScore")
	@Operation(summary = "상품별 리뷰 점수의 평균과 갯수 조회")
	public BaseResponse<ReviewScoreResponseVo> getReviewScore(
		@PathVariable String productUuid
	) {
		ReviewScoreResponseDto reviewScoreResponseDto = reviewService.getReviewScore(productUuid);

		if (reviewScoreResponseDto == null) {
			return new BaseResponse<>(
				HttpStatus.BAD_REQUEST,
				BaseResponseStatus.NO_EXIST_PRODUCT_DETAIL.isSuccess(),
				BaseResponseStatus.NO_EXIST_PRODUCT_DETAIL.getMessage(),
				BaseResponseStatus.NO_EXIST_PRODUCT_DETAIL.getCode(),
				null
			);
		}

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

		String authorName = authUserDetail.getNickname();

		if (authorName == null) {
			return new BaseResponse<>(
				HttpStatus.UNAUTHORIZED,
				BaseResponseStatus.TOKEN_NOT_VALID.isSuccess(),
				BaseResponseStatus.TOKEN_NOT_VALID.getMessage(),
				BaseResponseStatus.TOKEN_NOT_VALID.getCode(),
				null
			);
		}

		log.info("authorName: {}", authorName);
		reviewService.addReview(ReviewRequestDto.of(reviewRequestVo, authorName));

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			null
		);
	}

	@PutMapping("/{reviewUuid}")
	@Operation(summary = "리뷰 수정", description = "기존에 작성된 리뷰를 수정합니다.")
	public BaseResponse<Void> updateReview(
		@PathVariable String reviewUuid,
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody ReviewModifyRequestVo reviewModifyRequestVo) {

		if (authUserDetail == null) {
			return new BaseResponse<>(
				HttpStatus.UNAUTHORIZED,
				BaseResponseStatus.TOKEN_NOT_VALID.isSuccess(),
				BaseResponseStatus.TOKEN_NOT_VALID.getMessage(),
				BaseResponseStatus.TOKEN_NOT_VALID.getCode(),
				null
			);
		}

		// authorName이 같으먄 수정가능
		reviewService.modifyReview(ReviewModifyRequestDto.of(reviewModifyRequestVo), reviewUuid);

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			null
		);

	}

	@DeleteMapping("/{id}/reviewDelete")
	@Operation(summary = "댓글 삭제", description = "작성했던 리뷰를 삭제합니다.")
	public BaseResponse<Void> deleteReview(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable Long id) {

		if (authUserDetail == null) {
			return new BaseResponse<>(
				HttpStatus.UNAUTHORIZED,
				BaseResponseStatus.TOKEN_NOT_VALID.isSuccess(),
				BaseResponseStatus.TOKEN_NOT_VALID.getMessage(),
				BaseResponseStatus.TOKEN_NOT_VALID.getCode(),
				null
			);
		}

		reviewService.deleteReview(id);

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			null
		);
	}

}

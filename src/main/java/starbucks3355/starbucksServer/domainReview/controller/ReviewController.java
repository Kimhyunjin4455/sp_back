package starbucks3355.starbucksServer.domainReview.controller;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
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
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.entity.CommonResponseSliceEntity;
import starbucks3355.starbucksServer.domainReview.dto.in.ReviewRequestDto;
import starbucks3355.starbucksServer.domainReview.dto.out.MyReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ProductReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.service.ReviewService;
import starbucks3355.starbucksServer.domainReview.vo.in.ReviewRequestVo;
import starbucks3355.starbucksServer.domainReview.vo.out.MyReviewResponseVo;
import starbucks3355.starbucksServer.domainReview.vo.out.ProductReviewResponseVo;
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
	public CommonResponseSliceEntity<List<ProductReviewResponseVo>> getProductReviews(
		@PathVariable String productUuid,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {
		Slice<ProductReviewResponseDto> productReviewResponseDtoSlice = reviewService.getProductReviews(productUuid,
			page, size);

		List<ProductReviewResponseVo> responseVoList = productReviewResponseDtoSlice.stream()
			.map(ProductReviewResponseDto::dtoToResponseVo)
			.toList();

		return new CommonResponseSliceEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			responseVoList,
			productReviewResponseDtoSlice.hasNext()
		);
	}

	@GetMapping("/{productUuid}/allReviewsHaveMediaOfProduct")
	@Operation(summary = "상품별 리뷰 전체 조회(이미지가 있는 리뷰만)")
	public CommonResponseEntity<List<ProductReviewResponseVo>> getProductReviewsHaveMedia(
		@PathVariable String productUuid) {
		List<ProductReviewResponseDto> productReviewResponseDtoList = reviewService.getProductReviewsHaveMedia(
			productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			productReviewResponseDtoList.stream()
				.map(ProductReviewResponseDto::dtoToResponseVo)
				.toList()
		);
	}

	@GetMapping("/{memberUuid}/allReviewsOfMember")
	@Operation(summary = "회원별 리뷰 전체 조회")
	public CommonResponseEntity<List<MyReviewResponseVo>> getMemberReviews(
		@PathVariable String memberUuid) {
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
		return new CommonResponseEntity<ReviewResponseVo>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			reviewResponseDto.dtoToResponseVo()
		);
	}

	@PostMapping("/{reviewUuid}")
	@Operation(summary = "리뷰 한개 등록")
	public CommonResponseEntity<Void> addReview(
		@RequestBody ReviewRequestVo reviewRequestVo) {

		ReviewRequestDto reviewRequestDto = ReviewRequestDto.builder()
			.content(reviewRequestVo.getContent())
			.reviewUuid(reviewRequestVo.getReviewUuid())
			.reviewScore(reviewRequestVo.getReviewScore())
			.productUuid(reviewRequestVo.getProductUuid())
			.memberUuid(reviewRequestVo.getMemberUuid())
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
		@RequestBody ReviewRequestVo reviewRequestVo) {

		ReviewRequestDto reviewRequestDto = ReviewRequestDto.builder()
			.content(reviewRequestVo.getContent())
			.reviewUuid(reviewRequestVo.getReviewUuid())
			.reviewScore(reviewRequestVo.getReviewScore())
			.productUuid(reviewRequestVo.getProductUuid())
			.memberUuid(reviewRequestVo.getMemberUuid())
			.regDate(reviewRequestVo.getRegDate())
			.modDate(reviewRequestVo.getModDate())
			.build();

		reviewService.modifyReview(reviewRequestDto, reviewUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null
		);

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "댓글 삭제", description = "작성했던 리뷰를 삭제합니다.")
	public CommonResponseEntity<Void> deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null
		);
	}

}

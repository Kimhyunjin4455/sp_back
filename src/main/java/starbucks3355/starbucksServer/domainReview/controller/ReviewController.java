package starbucks3355.starbucksServer.domainReview.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.domainReview.dto.out.ReviewResponseDto;
import starbucks3355.starbucksServer.domainReview.service.ReviewService;
import starbucks3355.starbucksServer.domainReview.vo.out.ReviewResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
@Tag(name = "Review", description = "리뷰 API")
public class ReviewController {
	private final ReviewService reviewService;

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
}

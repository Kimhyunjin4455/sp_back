package starbucks3355.starbucksServer.domainCoupon.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.entity.CommonResponseSliceEntity;
import starbucks3355.starbucksServer.common.jwt.JwtTokenProvider;
import starbucks3355.starbucksServer.domainCoupon.dto.request.CouponRequestDto;
import starbucks3355.starbucksServer.domainCoupon.dto.response.CouponResponseDto;
import starbucks3355.starbucksServer.domainCoupon.service.CouponService;
import starbucks3355.starbucksServer.domainCoupon.vo.response.CouponResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
	private final CouponService couponService;
	private final JwtTokenProvider provider;

	@PostMapping("/createcoupon")
	@Operation(summary = "쿠폰 등록")
	public CommonResponseEntity<CouponResponseDto> createCoupon(@RequestBody CouponRequestDto requestDto) {
		CouponResponseDto responseDto = couponService.createCoupon(requestDto);
		return new CommonResponseEntity<>(
			HttpStatus.CREATED,
			CommonResponseMessage.SUCCESS.getMessage(),
			responseDto
		);
	}

	@DeleteMapping("/deletecoupon/{id}")
	public CommonResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
		couponService.deleteCoupon(id);
		return new CommonResponseEntity<>(
			HttpStatus.NO_CONTENT,
			CommonResponseMessage.SUCCESS.getMessage(),
			null
		);
	}

	// 쿠폰 목록 조회 (스크롤)
	@GetMapping("/couponlist")
	@Operation(summary = "보유 쿠폰 목록 조회")
	public CommonResponseSliceEntity<List<CouponResponseVo>> getAllCoupons(
		@RequestHeader("Authorization") String accessToken,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size
	) {
		String uuid = provider.parseUuid(accessToken);

		Slice<CouponResponseDto> couponResponseDtos = couponService.getAllCoupons(uuid, page, size);

		List<CouponResponseVo> couponResponseVos = couponResponseDtos.stream()
			.map(CouponResponseDto::toVo)
			.collect(Collectors.toList());

		return new CommonResponseSliceEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			couponResponseVos,
			couponResponseDtos.hasNext()
		);
	}

}

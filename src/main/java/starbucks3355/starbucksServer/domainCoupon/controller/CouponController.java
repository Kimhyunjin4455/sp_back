package starbucks3355.starbucksServer.domainCoupon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.domainCoupon.dto.request.CouponRequestDto;
import starbucks3355.starbucksServer.domainCoupon.dto.response.CouponResponseDto;
import starbucks3355.starbucksServer.domainCoupon.service.CouponService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
	private final CouponService couponService;

	@PostMapping("/createcoupon")
	@Operation(summary = "쿠폰 등록")
	public ResponseEntity<CouponResponseDto> createCoupon(@RequestBody CouponRequestDto requestDto) {
		CouponResponseDto responseDto = couponService.createCoupon(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@DeleteMapping("/deletecoupon/{id}")
	public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
		couponService.deleteCoupon(id);
		return ResponseEntity.noContent().build();
	}

	// 쿠폰 목록 조회 (스크롤)

}

package starbucks3355.starbucksServer.domainCoupon.service;

import java.util.List;

import starbucks3355.starbucksServer.domainCoupon.dto.request.CouponRequestDto;
import starbucks3355.starbucksServer.domainCoupon.dto.response.CouponResponseDto;

public interface CouponService {
	CouponResponseDto createCoupon(CouponRequestDto couponRequestDto);
	void deleteCoupon(Long id);
	// Integer applyCoupon(String couponCode, Integer originalPrice);
	CouponRequestDto issueCoupon(String couponCode);
	List<CouponResponseDto> getAllCoupons();
}

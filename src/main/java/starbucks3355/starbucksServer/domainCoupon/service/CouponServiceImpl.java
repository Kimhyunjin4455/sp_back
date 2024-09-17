package starbucks3355.starbucksServer.domainCoupon.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainCoupon.dto.request.CouponRequestDto;
import starbucks3355.starbucksServer.domainCoupon.dto.response.CouponResponseDto;
import starbucks3355.starbucksServer.domainCoupon.entity.Coupon;
import starbucks3355.starbucksServer.domainCoupon.repository.CouponRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponServiceImpl implements CouponService {

	private final CouponRepository couponRepository;

	@Override
	public CouponResponseDto createCoupon(CouponRequestDto requestDto) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime validateDate = now.plusDays(30); // 유효기간 30일 후로 설정

		Coupon coupon = Coupon.builder()
			.couponName(requestDto.getCouponName())
			.couponCode(requestDto.getCouponCode())
			.createDate(now)
			.validateDate(validateDate)
			.useCondition(true)
			.discountValue(requestDto.getDiscountValue())
			.build();

		Coupon savedCoupon = couponRepository.save(coupon);
		return CouponResponseDto.builder()
			.id(savedCoupon.getId())
			.couponName(savedCoupon.getCouponName())
			.couponCode(savedCoupon.getCouponCode())
			.createDate(savedCoupon.getCreateDate()) // 현재 시간으로 설정
			.useCondition(savedCoupon.isUseCondition())
			.validateDate(savedCoupon.getValidateDate())
			.discountValue(savedCoupon.getDiscountValue())
			.build();

	}

	@Override
	public void deleteCoupon(Long id) {
		couponRepository.deleteById(id);

	}

	@Override
	public CouponRequestDto issueCoupon(String couponCode) {
		// 쿠폰 발급 로직 구현
		return null;
	}

	// 모든 쿠폰 조회 로직 구현
	@Override
	public Slice<CouponResponseDto> getAllCoupons(String uuid, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Slice<Coupon> coupons = couponRepository.findAll(pageable);

		return coupons.map(coupon -> CouponResponseDto.builder()
			.id(coupon.getId())
			.couponName(coupon.getCouponName())
			.couponCode(coupon.getCouponCode())
			.createDate(coupon.getCreateDate()) // 현재 시간으로 설정
			.validateDate(coupon.getValidateDate())
			.useCondition(coupon.isUseCondition())
			.discountValue(coupon.getDiscountValue())
			.build());
	}
}

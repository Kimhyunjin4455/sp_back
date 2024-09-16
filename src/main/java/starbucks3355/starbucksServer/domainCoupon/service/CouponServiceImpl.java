package starbucks3355.starbucksServer.domainCoupon.service;

import java.time.LocalDateTime;
import java.util.List;

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
		Coupon coupon = Coupon.builder()
			.couponName(requestDto.getCouponName())
			.couponCode(requestDto.getCouponCode())
			.createDate(LocalDateTime.now())
			.useCondition(true)
			.discountValue(requestDto.getDiscountValue())
			.build();

		Coupon savedCoupon = couponRepository.save(coupon);
		return CouponResponseDto.builder()
			.id(savedCoupon.getId())
			.couponName(savedCoupon.getCouponName())
			.couponCode(savedCoupon.getCouponCode())
			.createDate(savedCoupon.getCreateDate()) // 현재 시간으로 설정
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

	@Override
	public List<CouponResponseDto> getAllCoupons() {
		// 모든 쿠폰 조회 로직 구현
		return null;
	}
}

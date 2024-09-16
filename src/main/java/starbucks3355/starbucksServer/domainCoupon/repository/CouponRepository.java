package starbucks3355.starbucksServer.domainCoupon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.domainCoupon.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	Optional<Coupon> findByCouponCode(String couponCode);

}

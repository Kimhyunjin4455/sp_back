package starbucks3355.starbucksServer.domainCoupon.vo.request;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class CouponRequestVo {
	private String couponName;
	private String couponCode;
	private Boolean useCondition;
	private Integer discountValue;
}

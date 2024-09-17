package starbucks3355.starbucksServer.domainCoupon.dto.request;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainCoupon.vo.request.CouponRequestVo;

@Getter
@NoArgsConstructor
public class CouponRequestDto {
	private String uuid;
	private String couponName;
	private String couponCode;
	private Boolean useCondition;
	private Integer discountValue;

	@Builder
	public CouponRequestDto (
		String uuid,
		String couponName,
		String couponCode,
		Boolean useCondition,
		Integer discountValue
	) {
		this.uuid = uuid;
		this.couponName = couponName;
		this.couponCode = couponCode;
		this.useCondition = useCondition;
		this.discountValue = discountValue;
	}
	public static CouponRequestDto from(CouponRequestVo couponRequestVo) {
		return CouponRequestDto.builder()
			.uuid(couponRequestVo.getUuid())
			.couponName(couponRequestVo.getCouponName())
			.couponCode(couponRequestVo.getCouponCode())
			.discountValue(couponRequestVo.getDiscountValue())
			.build();
	}
}

package starbucks3355.starbucksServer.domainCoupon.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainCoupon.vo.response.CouponResponseVo;

@Getter
@NoArgsConstructor
public class CouponResponseDto {
	private Long id;
	private String couponName;
	private String couponCode;
	private LocalDateTime createDate;
	private LocalDateTime validateDate;
	private Boolean useCondition;
	private Integer discountValue;


	@Builder
	public CouponResponseDto (
		Long id,
		String couponName,
		String couponCode,
		LocalDateTime createDate,
		LocalDateTime validateDate,
		Boolean useCondition,
		Integer discountValue
	) {
		this.id = id;
		this.couponName = couponName;
		this.couponCode = couponCode;
		this.createDate = createDate;
		this.validateDate = validateDate;
		this.useCondition = useCondition;
		this.discountValue = discountValue;
	}
	public CouponResponseVo toVo() {
		return CouponResponseVo.builder()
			.id(id)
			.couponName(couponName)
			.couponCode(couponCode)
			.createDate(createDate)
			.validateDate(validateDate)
			.useCondition(useCondition)
			.discountValue(discountValue)
			.build();
	}

}

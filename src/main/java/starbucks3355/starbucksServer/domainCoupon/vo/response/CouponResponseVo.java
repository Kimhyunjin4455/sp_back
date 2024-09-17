package starbucks3355.starbucksServer.domainCoupon.vo.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CouponResponseVo {
	private Long id;
	private String couponName;
	private String couponCode;
	private LocalDateTime createDate;
	private LocalDateTime validateDate;
	private Boolean useCondition;
	private Integer discountValue;
	private String uuid;

	@Builder
	public CouponResponseVo(
		Long id,
		String couponName,
		String couponCode,
		LocalDateTime createDate,
		LocalDateTime validateDate,
		Boolean useCondition,
		Integer discountValue,
		String uuid
	) {
		this.id = id;
		this.couponName = couponName;
		this.couponCode = couponCode;
		this.createDate = createDate;
		this.validateDate = validateDate;
		this.useCondition = useCondition;
		this.discountValue = discountValue;
		this.uuid = uuid;
	}
}

package starbucks3355.starbucksServer.domainCoupon.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	@Column(nullable = false, length = 255)
	private String couponName;
	@Column(nullable = false, length = 255, unique = true)
	private String couponCode;
	@Column(nullable = false)
	private LocalDateTime createDate;
	@Column(nullable = false)
	private LocalDateTime validateDate;
	@Column(nullable = false)
	private boolean useCondition;
	@Column(nullable = false)
	private Integer discountValue;

	@Builder
	public Coupon(
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

}

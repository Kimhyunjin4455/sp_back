package starbucks3355.starbucksServer.domainMember.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long couponId;
	@Column(nullable = false, length = 255)
	private String couponName;
	@Column(nullable = false, length = 255, unique = true)
	private String couponNumber;
	@Column(nullable = false)
	private LocalDateTime createDate;
	@Column(nullable = false)
	private LocalDateTime validateDate;
	@Column(nullable = false)
	private Long couponQuantity;
	@Column(length = 255)
	private String useCondition;
	private Integer discountType;
	private Integer discountValue;
	private Integer discountLimit;
	private Integer couponLimit;

}

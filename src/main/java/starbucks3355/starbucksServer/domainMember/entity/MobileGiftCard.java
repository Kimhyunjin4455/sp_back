package starbucks3355.starbucksServer.domainMember.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class MobileGiftCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long mobileGiftCardId;
	private Integer discountAmount;
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	private LocalDateTime usageDate;
	@Column(nullable = false)
	private LocalDateTime expirationDate;
	@Column(nullable = false)
	private Boolean usageState;
	@Column(nullable = false)
	private Boolean registrationStatus;
	@Column(nullable = false)
	private UUID memberUuid;
}

package starbucks3355.starbucksServer.domainOrders.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class TermsConditions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 30)
	private Long id;
	@Column(nullable = true, length = 300)
	private String termsName;
	@Column(nullable = true, length = 40)
	private UUID uuId;
	@Column(nullable = false, length = 10)
	private Boolean isAgree;
	@ManyToOne
	@JoinColumn(name = "termsDetailId", nullable = false)
	private TermsConditions termsDetailId;
}

package starbucks3355.starbucksServer.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class OAuth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String providerEmail;
	@Column(nullable = false, length = 50)
	private String provider;
	@Column(nullable = false, length = 50)
	private String providerId;

	@Builder
	public OAuth(
		String providerEmail,
		String provider,
		String providerId
	) {
		this.providerEmail = providerEmail;
		this.provider = provider;
		this.providerId = providerId;
	}

}
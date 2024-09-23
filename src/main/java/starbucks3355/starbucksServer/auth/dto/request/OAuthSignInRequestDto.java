package starbucks3355.starbucksServer.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.auth.entity.OAuth;
import starbucks3355.starbucksServer.auth.vo.request.OAuthSignInRequestVo;

@Getter
@NoArgsConstructor
public class OAuthSignInRequestDto {

	private String provider;
	private String providerId;
	private String providerEmail;

	@Builder
	public OAuthSignInRequestDto(
		String provider,
		String providerId,
		String providerEmail
	) {
		this.provider = provider;
		this.providerId = providerId;
		this.providerEmail = providerEmail;
	}

	public OAuth toEntity(String userUuid) {
		return OAuth.builder()
			.provider(provider)
			.providerId(providerId)
			.providerEmail(providerEmail)
			.build();
	}

	public static OAuthSignInRequestDto of(OAuthSignInRequestVo vo) {
		return OAuthSignInRequestDto.builder()
			.provider(vo.getProvider())
			.providerId(vo.getProviderId())
			.providerEmail(vo.getProviderEmail())
			.build();
	}

}
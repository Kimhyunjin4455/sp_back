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
	private String email;

	@Builder
	public OAuthSignInRequestDto(
		String provider,
		String providerId,
		String email
	) {
		this.provider = provider;
		this.providerId = providerId;
		this.email = email;
	}

	public OAuth toEntity(String userUuid) {
		return OAuth.builder()
			.provider(provider)
			.providerId(providerId)
			.email(email)
			.build();
	}

	public static OAuthSignInRequestDto of(OAuthSignInRequestVo vo) {
		return OAuthSignInRequestDto.builder()
			.provider(vo.getProvider())
			.providerId(vo.getProviderId())
			.email(vo.getProviderEmail())
			.build();
	}

}
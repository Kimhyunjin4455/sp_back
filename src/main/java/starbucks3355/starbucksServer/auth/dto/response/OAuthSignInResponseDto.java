package starbucks3355.starbucksServer.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.auth.vo.response.OAuthSignInResponseVo;
import starbucks3355.starbucksServer.auth.vo.response.SignInResponseVo;
import starbucks3355.starbucksServer.domainMember.entity.Member;

@Getter
@NoArgsConstructor
public class OAuthSignInResponseDto {

	private String accessToken;
	private String userId;
	private String uuid;
	private boolean isRegistered;

	@Builder
	public OAuthSignInResponseDto(String accessToken, String userId, String uuid, boolean isRegistered) {
		this.accessToken = accessToken;
		this.userId = userId;
		this.uuid = uuid;
		this.isRegistered = isRegistered;
	}

	public OAuthSignInResponseVo toVo() {
		return OAuthSignInResponseVo.builder()
			.accessToken(accessToken)
			.userId(userId)
			.uuid(uuid)
			.isRegistered(isRegistered)
			.build();
	}

	public static OAuthSignInResponseDto from(Member member, String accessToken, boolean isRegistered) {
		return OAuthSignInResponseDto.builder()
			.accessToken(accessToken)
			.userId(member.getUserId())
			.uuid(member.getUuid())
			.isRegistered(isRegistered)
			.build();
	}

	public static OAuthSignInResponseDto from(boolean isRegistered) {
		return OAuthSignInResponseDto.builder()
			.accessToken(null)
			.userId(null)
			.uuid(null)
			.isRegistered(isRegistered)
			.build();
	}
}

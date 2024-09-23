package starbucks3355.starbucksServer.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.auth.vo.response.SignInResponseVo;
import starbucks3355.starbucksServer.domainMember.entity.Member;

@Getter
@NoArgsConstructor
public class SignInResponseDto {

	private String accessToken;
	private String userId;
	private String uuid;
	private boolean isRegistered;

	@Builder
	public SignInResponseDto(String accessToken, String userId, String uuid, boolean isRegistered) {
		this.accessToken = accessToken;
		this.userId = userId;
		this.uuid = uuid;
		this.isRegistered = isRegistered;
	}

	public SignInResponseVo toVo() {
		return SignInResponseVo.builder()
			.accessToken(accessToken)
			.userId(userId)
			.uuid(uuid)
			.build();
	}

	public static SignInResponseDto from(Member member, String accessToken, boolean isRegistered) {
		if (member == null) {
			return SignInResponseDto.builder()
				.accessToken(null)
				.userId(null)
				.uuid(null)
				.isRegistered(isRegistered)
				.build();
		}

		return SignInResponseDto.builder()
			.accessToken(accessToken)
			.userId(member.getUserId())
			.uuid(member.getUuid())
			.isRegistered(isRegistered)
			.build();
	}
}

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

	@Builder
	public SignInResponseDto(String accessToken, String userId, String uuid) {
		this.accessToken = accessToken;
		this.userId = userId;
		this.uuid = uuid;
	}

	public SignInResponseVo toVo() {
		return SignInResponseVo.builder()
			.accessToken(accessToken)
			.userId(userId)
			.uuid(uuid)
			.build();
	}

	public static SignInResponseDto from(Member member, String accessToken) {
		return SignInResponseDto.builder()
			.accessToken(accessToken)
			.userId(member.getUserId())
			.uuid(member.getUuid())
			.build();
	}
}

package starbucks3355.starbucksServer.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.auth.vo.response.FindPasswordResponseVo;

@Getter
@NoArgsConstructor
public class FindPasswordResponseDto {
	private String accessToken;

	private String nickname;
	private boolean isUser;


	@Builder
	public FindPasswordResponseDto(String accessToken, String nickname, boolean isUser) {
		this.accessToken = accessToken;
		this.nickname = nickname;
		this.isUser = isUser;
	}

	public FindPasswordResponseVo toVo() {
		return FindPasswordResponseVo.builder()
			.accessToken(accessToken)
			.nickname(nickname)
			.isUser(isUser)
			.build();
	}

}

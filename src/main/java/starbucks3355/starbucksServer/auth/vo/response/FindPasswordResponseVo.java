package starbucks3355.starbucksServer.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindPasswordResponseVo {
	private String accessToken;
	private String nickname;
	private boolean isUser;

	@Builder
	public FindPasswordResponseVo(String accessToken, String nickname, boolean isUser) {
		this.accessToken = accessToken;
		this.nickname = nickname;
		this.isUser = isUser;
	}
}

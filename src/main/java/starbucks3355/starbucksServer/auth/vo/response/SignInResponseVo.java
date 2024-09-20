package starbucks3355.starbucksServer.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponseVo {

	private String accessToken;
	private String userId;
	private String uuid;

	@Builder
	public SignInResponseVo(String accessToken, String userId, String uuid) {
		this.accessToken = accessToken;
		this.userId = userId;
		this.uuid = uuid;
	}
}

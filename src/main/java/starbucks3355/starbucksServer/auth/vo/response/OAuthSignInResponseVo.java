package starbucks3355.starbucksServer.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OAuthSignInResponseVo {

	private String accessToken;
	private String userId;
	private String uuid;
	private boolean isRegistered;

	@Builder
	public OAuthSignInResponseVo(String accessToken, String userId, String uuid, boolean isRegistered) {
		this.accessToken = accessToken;
		this.userId = userId;
		this.uuid = uuid;
		this.isRegistered = isRegistered;
	}
}

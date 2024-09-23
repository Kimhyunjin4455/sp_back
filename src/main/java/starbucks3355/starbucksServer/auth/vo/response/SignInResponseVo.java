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

	private boolean isRegsitered;

	@Builder
	public SignInResponseVo(String accessToken, String userId, String uuid, boolean isRegsitered) {
		this.accessToken = accessToken;
		this.userId = userId;
		this.uuid = uuid;
		this.isRegsitered = isRegsitered;
	}
}

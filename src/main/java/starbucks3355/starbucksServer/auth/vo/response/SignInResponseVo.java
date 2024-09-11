package starbucks3355.starbucksServer.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponseVo {

	private String accessToken;
	private String name;
	private String uuid;

	@Builder
	public SignInResponseVo(String accessToken, String name, String uuid) {
		this.accessToken = accessToken;
		this.name = name;
		this.uuid = uuid;
	}
}

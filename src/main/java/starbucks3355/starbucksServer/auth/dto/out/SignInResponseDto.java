package starbucks3355.starbucksServer.auth.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.auth.vo.out.SignInResponseVo;

@Getter
@NoArgsConstructor
public class SignInResponseDto {

	private String accessToken;
	private String name;
	private String uuid;

	@Builder
	public SignInResponseDto(String accessToken, String name, String uuid) {
		this.accessToken = accessToken;
		this.name = name;
		this.uuid = uuid;
	}

	public SignInResponseVo toVo() {
		return SignInResponseVo.builder()
			.accessToken(accessToken)
			.name(name)
			.uuid(uuid)
			.build();
	}

}

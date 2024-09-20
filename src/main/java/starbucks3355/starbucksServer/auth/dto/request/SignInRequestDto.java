package starbucks3355.starbucksServer.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.auth.vo.request.SignInRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class SignInRequestDto {
	private String userId;
	private String password;

	@Builder
	public SignInRequestDto(String email, String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	public static SignInRequestDto from(SignInRequestVo signInRequestVo) {
		return SignInRequestDto.builder()
			.userId(signInRequestVo.getUserId())
			.password(signInRequestVo.getPassword())
			.build();
	}
}

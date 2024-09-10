package starbucks3355.starbucksServer.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.auth.vo.in.SignInRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class SignInRequestDto {

	private String email;
	private String password;

	@Builder
	public SignInRequestDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public static SignInRequestDto from(SignInRequestVo signInRequestVo) {
		return SignInRequestDto.builder()
			.email(signInRequestVo.getEmail())
			.password(signInRequestVo.getPassword())
			.build();
	}
}

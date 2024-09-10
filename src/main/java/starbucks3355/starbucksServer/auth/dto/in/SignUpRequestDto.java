package starbucks3355.starbucksServer.auth.dto.in;

import java.util.Date;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.auth.vo.in.SignUpRequestVo;
import starbucks3355.starbucksServer.domainMember.entity.Member;

@Getter
@NoArgsConstructor
@ToString
public class SignUpRequestDto {

	private String userId;
	private String email;
	private String password;
	private String name;
	private String nickname;


	public Member toEntity(PasswordEncoder passwordEncoder) {

		return Member.builder()
			.userId(userId)
			.email(email)
			.password(passwordEncoder.encode(password))
			.name(name)
			.nickname(nickname)
			.uuid(UUID.randomUUID().toString())
			.build();
	}

	@Builder
	public SignUpRequestDto(
		String userId,
		String email,
		String password,
		String name,
		String nickname
	) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
	}

	public static SignUpRequestDto from(SignUpRequestVo signUpRequestVo) {
		return SignUpRequestDto.builder()
			.userId(signUpRequestVo.getUserId())
			.email(signUpRequestVo.getEmail())
			.password(signUpRequestVo.getPassword())
			.name(signUpRequestVo.getName())
			.nickname(signUpRequestVo.getNickname())
			.build();
	}
}

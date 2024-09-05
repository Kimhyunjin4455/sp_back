package starbucks3355.starbucksServer.domainMember.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainMember.entity.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {
	private String userId;
	private String password;

	public Member toEntity(LoginRequestDto loginRequestDto) {
		return Member.builder()
			.userId(loginRequestDto.userId)
			.password(loginRequestDto.password)
			.build();
	}
}

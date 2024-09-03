package starbucks3355.starbucksServer.domainMember.dto.requestDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainMember.entity.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDto {

	private String userId;
	private String password;
	private String email;
	private String nickname;


	public Member toEntity(MemberRequestDto memberRequestDto) {
		return Member.builder()
			.userId(memberRequestDto.userId)
			.password(memberRequestDto.password)
			.email(memberRequestDto.email)
			.nickname(memberRequestDto.nickname)
			.build();
	}
}





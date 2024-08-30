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
	private String phoneNumber;
	private LocalDate birth;
	private String address;
	private String gender;
	private String nickname;
	private UUID memberUuid;
	private Boolean isMember;
	private LocalDateTime withdrawalTime;

	public Member toEntity(MemberRequestDto memberRequestDto) {
		return Member.builder()
			.isMember(memberRequestDto.isMember)
			.userId(memberRequestDto.userId)
			.address(memberRequestDto.address)
			.memberUuid(memberRequestDto.memberUuid)
			.address(memberRequestDto.address)
			.gender(memberRequestDto.gender)
			.nickname(memberRequestDto.nickname)
			.memberUuid(memberRequestDto.memberUuid)
			.birth(memberRequestDto.birth)
			.phoneNumber(memberRequestDto.phoneNumber)
			.withdrawalTime(memberRequestDto.withdrawalTime)
			.build();
	}
}





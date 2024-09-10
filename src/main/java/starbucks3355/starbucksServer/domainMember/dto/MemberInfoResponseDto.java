package starbucks3355.starbucksServer.domainMember.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.vo.MemberInfoResponseVo;

@Getter
@NoArgsConstructor
public class MemberInfoResponseDto {

	private String userId;
	private String password;
	private String email;
	private String nickname;

	@Builder
	public MemberInfoResponseDto(
		String userId,
		String password,
		String email,
		String nickname
	) {
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
	}

	public static MemberInfoResponseDto from(Member member) {
		return MemberInfoResponseDto.builder()
			.userId(member.getUserId())
			.password(member.getPassword())
			.email(member.getEmail())
			.nickname(member.getNickname())
			.build();
	}

	public MemberInfoResponseVo toVo() {
		return MemberInfoResponseVo.builder()
			.userId(userId)
			.password(password)
			.email(email)
			.nickname(nickname)
			.build();

	}
}

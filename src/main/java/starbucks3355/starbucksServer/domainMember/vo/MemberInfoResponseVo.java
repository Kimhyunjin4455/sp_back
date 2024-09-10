package starbucks3355.starbucksServer.domainMember.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberInfoResponseVo {

	private String userId;
	private String email;
	private String nickname;

	@Builder
	public MemberInfoResponseVo(
		String userId,
		String email,
		String nickname
	) {
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
	}
}

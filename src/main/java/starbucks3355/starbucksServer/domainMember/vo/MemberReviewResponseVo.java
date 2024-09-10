package starbucks3355.starbucksServer.domainMember.vo;

import lombok.Builder;

public class MemberReviewResponseVo {
	private String uuid;
	private String nickname;

	@Builder
	public MemberReviewResponseVo(
		String uuid,
		String nickname
	) {
		this.uuid = uuid;
		this.nickname = nickname;
	}
}

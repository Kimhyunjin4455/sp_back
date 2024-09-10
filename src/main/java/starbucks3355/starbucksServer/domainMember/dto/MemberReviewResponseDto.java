package starbucks3355.starbucksServer.domainMember.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.vo.MemberInfoResponseVo;
import starbucks3355.starbucksServer.domainMember.vo.MemberReviewResponseVo;

@Getter
@NoArgsConstructor
public class MemberReviewResponseDto {

	private String uuid;
	private String nickname;

	@Builder
	public MemberReviewResponseDto(
		String uuid,
		String nickname
	) {
		this.uuid = uuid;
		this.nickname = nickname;
	}

	public static MemberReviewResponseDto from(Member member) {
		return MemberReviewResponseDto.builder()
			.uuid(member.getUuid())
			.nickname(member.getNickname())
			.build();
	}

	public MemberReviewResponseVo toVo() {
		return MemberReviewResponseVo.builder() // MemberReviewResponseVo를 빌더로 생성
			.uuid(uuid)
			.nickname(nickname)
			.build();
	}
}


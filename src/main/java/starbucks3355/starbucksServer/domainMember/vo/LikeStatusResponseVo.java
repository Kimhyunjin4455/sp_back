package starbucks3355.starbucksServer.domainMember.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeStatusResponseVo {
	private boolean isLiked;

	@Builder
	public LikeStatusResponseVo(boolean isLiked) {
		this.isLiked = isLiked;
	}
}

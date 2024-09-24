package starbucks3355.starbucksServer.domainMember.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikesProductResponseVo {
	private Long id;
	private boolean isLiked;

	@Builder
	public LikesProductResponseVo(Long id, boolean isLiked) {
		this.id = id;
		this.isLiked = isLiked;
	}
}

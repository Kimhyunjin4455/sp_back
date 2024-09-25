package starbucks3355.starbucksServer.domainMember.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainMember.entity.Likes;
import starbucks3355.starbucksServer.domainMember.vo.LikesProductResponseVo;

@Getter
@NoArgsConstructor
public class LikeStatusResponseDto {

	private boolean isLiked;

	@Builder
	public LikeStatusResponseDto(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public LikesProductResponseVo toVo() {
		return LikesProductResponseVo.builder()
			.isLiked(isLiked)
			.build();
	}
	public static LikeStatusResponseDto from(Likes likes) {
		return LikeStatusResponseDto.builder()
			.isLiked(likes.isLiked())
			.build();
	}


}

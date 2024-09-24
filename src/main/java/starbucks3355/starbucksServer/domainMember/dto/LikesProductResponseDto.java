package starbucks3355.starbucksServer.domainMember.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainMember.entity.Likes;
import starbucks3355.starbucksServer.domainMember.vo.LikesProductResponseVo;

@Getter
@NoArgsConstructor
public class LikesProductResponseDto {

	private Long id;
	private boolean isLiked;

	@Builder
	public LikesProductResponseDto(Long id, boolean isLiked) {
		this.id = id;
		this.isLiked = isLiked;
	}

	public LikesProductResponseVo toVo() {
		return LikesProductResponseVo.builder()
			.id(id)
			.isLiked(isLiked)
			.build();
	}
	public static LikesProductResponseDto from(Likes likes) {
		return LikesProductResponseDto.builder()
			.id(likes.getId())
			.isLiked(likes.isLiked())
			.build();
	}


}

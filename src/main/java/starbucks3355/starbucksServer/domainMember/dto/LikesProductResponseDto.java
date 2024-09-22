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
	private String uuid;
	private String productUuid;
	private boolean isLiked;

	@Builder
	public LikesProductResponseDto(Long id, String uuid, String productUuid, boolean isLiked) {
		this.id = id;
		this.uuid = uuid;
		this.productUuid = productUuid;
		this.isLiked = isLiked;
	}

	public LikesProductResponseVo toVo() {
		return LikesProductResponseVo.builder()
			.id(id)
			.uuid(uuid)
			.productUuid(productUuid)
			.isLiked(isLiked)
			.build();
	}
	public static LikesProductResponseDto from(Likes likes) {
		return LikesProductResponseDto.builder()
			.id(likes.getId())
			.uuid(likes.getUuid())
			.productUuid(likes.getProductUuid())
			.isLiked(likes.isLiked())
			.build();
	}


}

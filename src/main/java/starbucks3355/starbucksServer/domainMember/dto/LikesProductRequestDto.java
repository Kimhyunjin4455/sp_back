package starbucks3355.starbucksServer.domainMember.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.domainMember.vo.LikesProductRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class LikesProductRequestDto {

	private String uuid;
	private String productUuid;
	private boolean isLiked;


	@Builder
	public LikesProductRequestDto(String uuid, String productUuid, boolean isLiked) {
		this.uuid = uuid;
		this.productUuid = productUuid;
		this.isLiked = isLiked;
	}
	public static LikesProductRequestDto from(LikesProductRequestVo likesProductRequestVo) {
		return LikesProductRequestDto.builder()
			.uuid(likesProductRequestVo.getUuid())
			.productUuid(likesProductRequestVo.getProductUuid())
			.isLiked(likesProductRequestVo.isLiked())
			.build();
	}
}

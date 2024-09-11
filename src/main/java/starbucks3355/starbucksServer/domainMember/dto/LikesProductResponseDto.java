package starbucks3355.starbucksServer.domainMember.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainMember.vo.LikesProductResponseVo;

@Getter
@NoArgsConstructor
public class LikesProductResponseDto {

	private Long id;
	private String uuid;
	private String productUuid;
	private boolean isLikes;

	@Builder
	public LikesProductResponseDto(Long id, String uuid, String productUuid, boolean isLikes) {
		this.id = id;
		this.uuid = uuid;
		this.productUuid = productUuid;
		this.isLikes = isLikes;
	}

	public LikesProductResponseVo toVo() {
		return LikesProductResponseVo.builder()
			.id(id)
			.uuid(uuid)
			.productUuid(productUuid)
			.isLikes(isLikes)
			.build();
	}


}

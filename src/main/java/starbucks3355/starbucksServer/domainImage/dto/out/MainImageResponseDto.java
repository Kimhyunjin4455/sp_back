package starbucks3355.starbucksServer.domainImage.dto.out;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainImage.vo.out.MainImageResponseVo;

@Builder
@Getter
public class MainImageResponseDto { // 메인이미지 응답 dto,
	private String s3url;
	private String imageName;
	private String thumbnailPath;
	private String imageUuid; // 이미지의 정보
	private String otherUuid; // 어떤것에 대한 이미지인지, 필요 없는 필드인지?
	private boolean isMainImage; // 프론트에서 메인이 잘 넘어왔는지 확인하기 위한 필드

	public MainImageResponseVo dtoToResponseVo() {
		return MainImageResponseVo.builder()
			.s3url(s3url)
			.imageName(imageName)
			.thumbnailPath(thumbnailPath)
			.imageUuid(imageUuid)
			.otherUuid(otherUuid)
			.isMainImage(isMainImage)
			.build();
	}

}

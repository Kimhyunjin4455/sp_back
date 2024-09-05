package starbucks3355.starbucksServer.domainImage.dto.out;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainImage.vo.ImageResponseVo;

@Builder
@Getter
public class ImageResponseDto {
	private String s3url;
	private String imageName;
	private String thumbnailPath;
	private String imageUuid; // 이미지의 정보
	private String otherUuid; // 어떤것에 대한 이미지인지, 필요 없는 필드인지?

	public ImageResponseDto(String s3url, String imageName, String thumbnailPath, String imageUuid, String otherUuid) {
		this.s3url = s3url;
		this.imageName = imageName;
		this.thumbnailPath = thumbnailPath;
		this.imageUuid = imageUuid;
		this.otherUuid = otherUuid;
	}

	public ImageResponseVo dtoToResponseVo() {
		return ImageResponseVo.builder()
			.s3url(s3url)
			.imageName(imageName)
			.thumbnailPath(thumbnailPath)
			.imageUuid(imageUuid)
			.otherUuid(otherUuid)
			.build();
	}

}

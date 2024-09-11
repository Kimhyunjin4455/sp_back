package starbucks3355.starbucksServer.domainImage.vo.in;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainImage.dto.in.ImageRequestDto;

@Getter
@Builder
public class ImageRequestVo {
	private String s3url;
	private String imageName;
	private String thumbnailPath;
	private String imageUuid;
	private String otherUuid;
	private boolean isMainImage;

	public ImageRequestDto voToDto() {
		return ImageRequestDto.builder()
			.s3url(s3url)
			.imageName(imageName)
			.thumbnailPath(thumbnailPath)
			.imageUuid(imageUuid)
			.otherUuid(otherUuid)
			.isMainImage(isMainImage)
			.build();
	}
}

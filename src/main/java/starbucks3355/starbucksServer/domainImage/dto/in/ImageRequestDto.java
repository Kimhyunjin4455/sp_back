package starbucks3355.starbucksServer.domainImage.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageRequestDto {
	private String s3url;
	private String imageName;
	private String thumbnailPath;
	private String imageUuid;
	private String otherUuid;
	private boolean isMainImage;

	public ImageRequestDto(String s3url, String imageName, String thumbnailPath, String imageUuid, String otherUuid,
		boolean isMainImage) {
		this.s3url = s3url;
		this.imageName = imageName;
		this.thumbnailPath = thumbnailPath;
		this.imageUuid = imageUuid;
		this.otherUuid = otherUuid;
		this.isMainImage = isMainImage;
	}

}

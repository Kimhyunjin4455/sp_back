package starbucks3355.starbucksServer.domainImage.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainImage.vo.in.ImageRequestVo;

@Getter
@NoArgsConstructor
public class ImageRequestDto {
	private String s3url;
	private String imageName;
	private String thumbnailPath;
	private String imageUuid;
	private String otherUuid;
	private boolean isMainImage;

	@Builder
	public ImageRequestDto(String s3url, String imageName, String thumbnailPath, String imageUuid, String otherUuid,
		boolean isMainImage) {
		this.s3url = s3url;
		this.imageName = imageName;
		this.thumbnailPath = thumbnailPath;
		this.imageUuid = imageUuid;
		this.otherUuid = otherUuid;
		this.isMainImage = isMainImage;
	}

	public static ImageRequestDto of(
		ImageRequestVo imageRequestVo,
		String s3url,
		String imageName,
		String thumbnailPath,
		String imageUuid
	) {
		return ImageRequestDto.builder()
			.s3url(s3url)
			.imageName(imageName)
			.thumbnailPath(thumbnailPath)
			.imageUuid(imageUuid)
			.otherUuid(imageRequestVo.getOtherUuid())
			//.isMainImage(imageRequestVo.isMainImage())
			.build();
	}

}

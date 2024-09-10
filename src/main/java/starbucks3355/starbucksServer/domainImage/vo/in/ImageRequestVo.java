package starbucks3355.starbucksServer.domainImage.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageRequestVo {
	private String s3url;
	private String imageName;
	private String thumbnailPath;
	private String imageUuid;
	private String otherUuid;
	private boolean isMainImage;
}

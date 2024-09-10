package starbucks3355.starbucksServer.domainImage.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MainImageResponseVo {
	private String s3url;
	private String imageName;
	private String thumbnailPath;
	private String imageUuid; // 이미지의 정보
	private String otherUuid; // 어떤것에 대한 이미지인지, 필요 없는 필드인지?
	private boolean isMainImage;
}

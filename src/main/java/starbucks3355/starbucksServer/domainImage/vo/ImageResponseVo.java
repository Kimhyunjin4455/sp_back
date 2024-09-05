package starbucks3355.starbucksServer.domainImage.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseVo { // 어떤 필드까지 필요한지?
	private String s3url;
	private String imageName;
	private String thumbnailPath;
	private String imageUuid; // 이미지의 정보
	private String otherUuid; // 어떤것에 대한 이미지인지, 필요 없는 필드인지?
}

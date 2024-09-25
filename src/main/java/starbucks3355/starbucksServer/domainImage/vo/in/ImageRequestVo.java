package starbucks3355.starbucksServer.domainImage.vo.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainImage.dto.in.ImageRequestDto;

@Getter
@Builder
public class ImageRequestVo {
	// private String s3url;
	// private String imageName;
	// private String thumbnailPath;
	// private String imageUuid;
	private String otherUuid;
	private List<MultipartFile> file;
	private boolean isMainImage;

	public ImageRequestDto voToDto() {
		return ImageRequestDto.builder()

			.otherUuid(otherUuid)
			.isMainImage(isMainImage)
			.build();
	}

}

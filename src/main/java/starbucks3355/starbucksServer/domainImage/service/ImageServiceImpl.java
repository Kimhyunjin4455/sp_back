package starbucks3355.starbucksServer.domainImage.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainImage.dto.out.ImageResponseDto;
import starbucks3355.starbucksServer.domainImage.entity.Image;
import starbucks3355.starbucksServer.domainImage.repository.ImageRepository;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
	private final ImageRepository imageRepository;

	@Override
	public ImageResponseDto getImage(String otherUuid) {
		Image image = imageRepository.findByOtherUuid(otherUuid).orElseThrow(
			() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다."));
		return ImageResponseDto.builder()
			.s3url(image.getS3url())
			.imageName(image.getImageName())
			.thumbnailPath(image.getThumbnailPath())
			.imageUuid(image.getImageUuid())
			.otherUuid(image.getOtherUuid())
			.build();
	}
}

package starbucks3355.starbucksServer.domainImage.service;

import java.util.List;

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
	public List<ImageResponseDto> getImages(String otherUuid) {
		List<Image> images = imageRepository.findByOtherUuid(otherUuid);

		if (images != null) {
			return images.stream()
				.map(image -> ImageResponseDto.builder()
					.s3url(image.getS3url())
					.imageName(image.getImageName())
					.thumbnailPath(image.getThumbnailPath())
					.imageUuid(image.getImageUuid())
					.otherUuid(image.getOtherUuid())
					.build()
				).toList();
		}

		return List.of();
	}
}

package starbucks3355.starbucksServer.domainImage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainImage.dto.in.ImageRequestDto;
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

	@Override
	public ImageResponseDto getMainImage(String otherUuid, boolean isMainImage) {
		Optional<Image> result = imageRepository.findByOtherUuidAndIsMainImage(otherUuid, isMainImage);

		Image mainImage = result.get();

		if (mainImage != null) {
			return ImageResponseDto.builder()
				.s3url(mainImage.getS3url())
				.imageName(mainImage.getImageName())
				.thumbnailPath(mainImage.getThumbnailPath())
				.imageUuid(mainImage.getImageUuid())
				.otherUuid(mainImage.getOtherUuid())
				.build();
		}

		return null;
	}

	@Override
	public void addImage(List<ImageRequestDto> imageRequestDtos) {

	}

	@Override
	public void modifyImage(ImageRequestDto imageRequestDto, String imageUuid) {

	}

	@Override
	public void deleteImage(Long id) {

	}
}

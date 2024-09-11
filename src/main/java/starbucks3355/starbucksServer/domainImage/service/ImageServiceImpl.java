package starbucks3355.starbucksServer.domainImage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
	public void addImages(List<ImageRequestDto> imageRequestDtos) {
		imageRequestDtos.stream()
			.map(imageRequestDto -> Image.builder()
				.s3url(imageRequestDto.getS3url())
				.imageName(imageRequestDto.getImageName())
				.thumbnailPath(imageRequestDto.getThumbnailPath())
				.imageUuid(imageRequestDto.getImageUuid())
				.otherUuid(imageRequestDto.getOtherUuid())
				.isMainImage(imageRequestDto.isMainImage())
				.build()
			).forEach(imageRepository::save);

	}

	@Override
	public void modifyImage(ImageRequestDto imageRequestDto, String imageUuid) {
		List<Image> imageList = imageRepository.findByOtherUuid(imageRequestDto.getOtherUuid());
	}

	@Override
	public void deleteImage(Long id, String otherUuid) {
		imageRepository.deleteById(id);
		// 남은 이미지가 존재할 경우 그 이미지중 첫번째가 메인이미지가 되도록 설정
		// 메인이미지가 없을 경우 null로 설정
		if (imageRepository.findByOtherUuid(otherUuid).size() > 0) {
			Image image = imageRepository.findByOtherUuid(otherUuid).get(0);
			image.modifyIsMainImage(true);
			imageRepository.save(image);
		}
	}

	@Override
	@Transactional
	public void deleteAllImages(String otherUuid) {
		imageRepository.deleteByOtherUuid(otherUuid);
	}
}

package starbucks3355.starbucksServer.domainImage.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.S3.service.FileService;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.domainImage.dto.in.ImageRequestDto;
import starbucks3355.starbucksServer.domainImage.dto.out.ImageResponseDto;
import starbucks3355.starbucksServer.domainImage.entity.Image;
import starbucks3355.starbucksServer.domainImage.repository.ImageRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
	private final ImageRepository imageRepository;
	private final FileService fileService;

	@Override
	public List<ImageResponseDto> getImages(String otherUuid) {
		List<Image> images = imageRepository.findByOtherUuid(otherUuid);

		if (images != null) {
			return images.stream()
				.map(image -> ImageResponseDto.builder()
					.s3url(image.getS3url())
					.imageName(image.getImageName())
					//.thumbnailPath(image.getThumbnailPath())
					.imageUuid(image.getImageUuid())
					//.otherUuid(image.getOtherUuid())
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
				//thumbnailPath(mainImage.getThumbnailPath())
				.imageUuid(mainImage.getImageUuid())
				//otherUuid(mainImage.getOtherUuid())
				.build();
		}

		return null;
	}

	@Override
	public void addImages(List<MultipartFile> file, String otherUuid) {

		//받은 데이터를 s3 저장하고 imageRepository에 저장(첫번째 요소는 메인이미지로 설정
		for (int i = 0; i < file.size(); i++) {
			String result = saveImageToS3(file.get(i), otherUuid);
			imageRepository.save(Image.builder()
				.s3url(result)
				.imageName(file.get(i).getOriginalFilename())
				.imageUuid(UUID.randomUUID().toString())
				.otherUuid(otherUuid)
				.isMainImage(i == 0)
				.build());
		}

		//String result = saveImageToS3(file, otherUuid);
		// 받은 데이터를 Image 엔티티에 저장

		// imageRepository.save(Image.builder()
		// 	.s3url(result)
		// 	.imageName(file.getOriginalFilename())
		// 	.imageUuid(UUID.randomUUID().toString())
		// 	.otherUuid(otherUuid)
		// 	.isMainImage(true)
		// 	.build());

		//log.info(result);

		// 받은값을 db 저장

		// for (int i = 0; i < imageRequestDtos.size(); i++) {
		// 	ImageRequestDto dto = imageRequestDtos.get(i);
		// 	// 이미지 저장
		// 	Image image = Image.builder()
		// 		.s3url(imageRequestDtos.get(i).getS3url())
		// 		.imageName(dto.getImageName())
		// 		.thumbnailPath(dto.getThumbnailPath())
		// 		.imageUuid(dto.getImageUuid())
		// 		.otherUuid(dto.getOtherUuid())
		// 		.isMainImage(i == 0)
		// 		.build();
		//
		// }
		// // 이미지들을 넣되 0번째 인덱스는 isMainImage를 true로 설정
		// for (int i = 0; i < imageRequestDtos.size(); i++) {
		// 	Image image = Image.builder()
		// 		.s3url(imageRequestDtos.get(i).getS3url())
		// 		.imageName(imageRequestDtos.get(i).getImageName())
		// 		.thumbnailPath(imageRequestDtos.get(i).getThumbnailPath())
		// 		.imageUuid(imageRequestDtos.get(i).getImageUuid())
		// 		.otherUuid(imageRequestDtos.get(i).getOtherUuid())
		// 		.isMainImage(i == 0)
		// 		.build();
		//
		// 	imageRepository.save(image);
		// }

	}

	private String saveImageToS3(MultipartFile file, String otherUuid) {
		try {
			return fileService.saveMedia(file, otherUuid); // S3에 파일 저장 후 URL 반환
		} catch (RuntimeException e) {
			throw new BaseException(BaseResponseStatus.FAILED_TO_ADD_S3);
		}
	}

	@Override
	public void modifyImage(ImageRequestDto imageRequestDto, String imageUuid) {
		List<Image> imageList = imageRepository.findByOtherUuid(imageRequestDto.getOtherUuid());
	}

	@Override
	@Transactional
	public void deleteImage(String s3url, String otherUuid) {
		imageRepository.deleteByS3url(s3url);
		// 남은 이미지가 존재할 경우 그 이미지중 첫번째가 메인이미지가 되도록 설정
		if (imageRepository.findByOtherUuid(otherUuid).size() > 0) {
			Image image = imageRepository.findByOtherUuid(otherUuid).get(0);
			image.modifyIsMainImage(true);
			imageRepository.save(image);
		}
	}

	@Override
	@Transactional
	public void deleteAllImages(String otherUuid) {
		// 리뷰의 경우는 수정 시 모든 이미지를 지우고 프론트의 vo 값을 통해 post 할 예정
		imageRepository.deleteByOtherUuid(otherUuid);
	}
}

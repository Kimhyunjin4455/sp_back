package starbucks3355.starbucksServer.domainImage.service;

import java.util.List;

import starbucks3355.starbucksServer.domainImage.dto.in.ImageRequestDto;
import starbucks3355.starbucksServer.domainImage.dto.out.ImageResponseDto;

public interface ImageService {
	public List<ImageResponseDto> getImages(String otherUuid);

	public ImageResponseDto getMainImage(String otherUuid, boolean isMainImage);

	void addImages(List<ImageRequestDto> imageRequestDtos);

	void modifyImage(ImageRequestDto imageRequestDto, String imageUuid);

	void deleteImage(Long id, String otherUuid);

	void deleteAllImages(String otherUuid);

}

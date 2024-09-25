package starbucks3355.starbucksServer.domainImage.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import starbucks3355.starbucksServer.domainImage.dto.in.ImageRequestDto;
import starbucks3355.starbucksServer.domainImage.dto.out.ImageResponseDto;

public interface ImageService {
	public List<ImageResponseDto> getImages(String otherUuid);

	public ImageResponseDto getMainImage(String otherUuid, boolean isMainImage);

	void addImages(List<MultipartFile> file, String otherUuid);

	void modifyImage(ImageRequestDto imageRequestDto, String imageUuid);

	void deleteImage(String imageName, String otherUuid);

	void deleteAllImages(String otherUuid);

}

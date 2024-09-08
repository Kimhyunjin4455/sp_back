package starbucks3355.starbucksServer.domainImage.service;

import java.util.List;

import starbucks3355.starbucksServer.domainImage.dto.out.ImageResponseDto;

public interface ImageService {
	public List<ImageResponseDto> getImages(String otherUuid);
}

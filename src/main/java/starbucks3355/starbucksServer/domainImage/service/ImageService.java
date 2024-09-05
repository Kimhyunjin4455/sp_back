package starbucks3355.starbucksServer.domainImage.service;

import starbucks3355.starbucksServer.domainImage.dto.out.ImageResponseDto;

public interface ImageService {
	public ImageResponseDto getImage(String otherUuid);
}

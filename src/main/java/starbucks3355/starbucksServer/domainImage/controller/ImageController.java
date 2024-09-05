package starbucks3355.starbucksServer.domainImage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.domainImage.dto.out.ImageResponseDto;
import starbucks3355.starbucksServer.domainImage.service.ImageService;
import starbucks3355.starbucksServer.domainImage.vo.ImageResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/image")
@Tag(name = "Product", description = "이미지 조회 API")
public class ImageController {
	private final ImageService imageService;

	@GetMapping("/{otherUuid}")
	@Operation(summary = "상품 대표 이미지 조회")
	public CommonResponseEntity<ImageResponseVo> getImage(
		@PathVariable String otherUuid
	) {
		ImageResponseDto imageDto = imageService.getImage(otherUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			imageDto.dtoToResponseVo()
		);
	}
}

package starbucks3355.starbucksServer.domainImage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.domainImage.dto.out.ImageResponseDto;
import starbucks3355.starbucksServer.domainImage.service.ImageService;
import starbucks3355.starbucksServer.domainImage.vo.in.ImageRequestVo;
import starbucks3355.starbucksServer.domainImage.vo.out.ImageResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/image")
@Tag(name = "Product", description = "이미지 조회 API")
public class ImageController {
	private final ImageService imageService;

	@GetMapping("/{otherUuid}/allMedias")
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 목록 이미지 조회")
	public CommonResponseEntity<List<ImageResponseVo>> getImages(
		@PathVariable String otherUuid
	) {
		List<ImageResponseDto> imageDtoList = imageService.getImages(otherUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			imageDtoList.stream()
				.map(ImageResponseDto::dtoToResponseVo)
				.toList()
		);
	}

	@GetMapping("/{otherUuid}/mainMedia")
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 메인 이미지 조회")
	public CommonResponseEntity<ImageResponseVo> getMainImage(
		@PathVariable String otherUuid
	) {
		ImageResponseDto imageDto = imageService.getMainImage(otherUuid, true);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			imageDto.dtoToResponseVo()
		);
	}

	@PostMapping("/{otherUuid}/addMedia")
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 이미지 추가")
	public CommonResponseEntity<Void> addImages(
		@RequestBody List<ImageRequestVo> imageRequestVoList,
		@AuthenticationPrincipal AuthUserDetail authUserDetail
	) {
		imageService.addImages(imageRequestVoList.stream()
			.map(ImageRequestVo::voToDto)
			.toList()
		);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null
		);
	}

	@DeleteMapping("/{otherUuid}/deleteMedia/{id}")
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 이미지 삭제")
	public CommonResponseEntity<Void> deleteImage(
		@PathVariable Long id, String otherUuid,
		@AuthenticationPrincipal AuthUserDetail authUserDetail
	) {
		imageService.deleteImage(id, otherUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null
		);
	}

	@DeleteMapping("/{otherUuid}/deleteAllMedia")
	// 개체(상품, 리뷰, 쿠폰)에 대한 모든 이미지 삭제(수정의 경우 기존 등록된 모든 이미지리스트를 삭제하고 새 리스트을 입력할 것)
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 모든 이미지 삭제")
	public CommonResponseEntity<Void> deleteAllImage(
		@PathVariable String otherUuid,
		@AuthenticationPrincipal AuthUserDetail authUserDetail햣
	) {
		imageService.deleteAllImages(otherUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null
		);
	}
}

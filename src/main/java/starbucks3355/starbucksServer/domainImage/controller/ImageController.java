package starbucks3355.starbucksServer.domainImage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.S3.service.AwsFileServiceImpl;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.domainImage.dto.out.ImageResponseDto;
import starbucks3355.starbucksServer.domainImage.service.ImageService;
import starbucks3355.starbucksServer.domainImage.vo.out.ImageResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/image")
@Tag(name = "Product", description = "이미지 조회 API")
public class ImageController {
	private final ImageService imageService;
	private final AwsFileServiceImpl awsFileServiceImpl;

	@GetMapping("/{otherUuid}/allMedias")
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 목록 이미지 조회")
	public BaseResponse<List<ImageResponseVo>> getImages(
		@PathVariable String otherUuid
	) {
		List<ImageResponseDto> imageDtoList = imageService.getImages(otherUuid);

		if (imageDtoList.isEmpty()) {
			return new BaseResponse<>(
				HttpStatus.NO_CONTENT,
				BaseResponseStatus.NO_EXIST_IMAGE.isSuccess(),
				BaseResponseStatus.NO_EXIST_IMAGE.getMessage(),
				BaseResponseStatus.NO_EXIST_IMAGE.getCode(),
				null
			);
		}

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			imageDtoList.stream()
				.map(ImageResponseDto::dtoToResponseVo)
				.toList()
		);
	}

	@GetMapping("/{otherUuid}/mainMedia")
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 메인 이미지 조회")
	public BaseResponse<ImageResponseVo> getMainImage(
		@PathVariable String otherUuid
	) {
		ImageResponseDto imageDto = imageService.getMainImage(otherUuid, true);

		if (imageDto == null) {
			return new BaseResponse<>(
				HttpStatus.NO_CONTENT,
				BaseResponseStatus.NO_EXIST_IMAGE.isSuccess(),
				BaseResponseStatus.NO_EXIST_IMAGE.getMessage(),
				BaseResponseStatus.NO_EXIST_IMAGE.getCode(),
				null
			);
		}

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			imageDto.dtoToResponseVo()
		);
	}

	@PostMapping("/{otherUuid}/addMedia")
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 이미지 추가")
	public BaseResponse<Void> addImages(
		// @RequestBody List<ImageRequestVo> imageRequestVoList,
		@RequestPart("file") List<MultipartFile> file,
		@RequestPart String otherUuid,
		@AuthenticationPrincipal AuthUserDetail authUserDetail
	) {

		if (file.isEmpty()) {
			return new BaseResponse<>(
				HttpStatus.BAD_REQUEST,
				BaseResponseStatus.WRONG_FILE_TYPE.isSuccess(),
				BaseResponseStatus.WRONG_FILE_TYPE.getMessage(),
				BaseResponseStatus.WRONG_FILE_TYPE.getCode(),
				null
			);
		}

		log.info(file.toString());
		imageService.addImages(file, otherUuid);

		// if (authUserDetail == null) {
		// 	return new BaseResponse<>(
		// 		HttpStatus.UNAUTHORIZED,
		// 		BaseResponseStatus.TOKEN_NOT_VALID.isSuccess(),
		// 		BaseResponseStatus.TOKEN_NOT_VALID.getMessage(),
		// 		BaseResponseStatus.TOKEN_NOT_VALID.getCode(),
		// 		null
		// 	);
		// }

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			null
		);
	}

	@DeleteMapping("/{otherUuid}/deleteMedia/s3url")
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 이미지 삭제")
	public BaseResponse<Void> deleteImage(
		@PathVariable String otherUuid,
		//@RequestParam String s3url,
		@RequestParam String fileName,
		@AuthenticationPrincipal AuthUserDetail authUserDetail
	) {
		if (authUserDetail == null) {
			return new BaseResponse<>(
				HttpStatus.UNAUTHORIZED,
				BaseResponseStatus.TOKEN_NOT_VALID.isSuccess(),
				BaseResponseStatus.TOKEN_NOT_VALID.getMessage(),
				BaseResponseStatus.TOKEN_NOT_VALID.getCode(),
				null
			);
		}

		imageService.deleteImage(fileName, otherUuid); // -> 이미지 url 통해 db에서 삭제
		awsFileServiceImpl.deleteMedia(fileName); // -> 이미지이름 통해 s3에서 삭제

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			null
		);
	}

	@DeleteMapping("/{otherUuid}/deleteAllMedia")
	// 개체(상품, 리뷰, 쿠폰)에 대한 모든 이미지 삭제(수정의 경우 기존 등록된 모든 이미지리스트를 삭제하고 새 리스트을 입력할 것) <- 프론트의 코드가 달라짐
	@Operation(summary = "개체(상품, 리뷰, 쿠폰)에 대한 모든 이미지 삭제")
	public BaseResponse<Void> deleteAllImage(
		@PathVariable String otherUuid,
		@AuthenticationPrincipal AuthUserDetail authUserDetail
	) {

		if (authUserDetail == null) {
			return new BaseResponse<>(
				HttpStatus.UNAUTHORIZED,
				BaseResponseStatus.TOKEN_NOT_VALID.isSuccess(),
				BaseResponseStatus.TOKEN_NOT_VALID.getMessage(),
				BaseResponseStatus.TOKEN_NOT_VALID.getCode(),
				null
			);
		}

		imageService.deleteAllImages(otherUuid);

		return new BaseResponse<>(
			HttpStatus.OK,
			BaseResponseStatus.SUCCESS.isSuccess(),
			BaseResponseStatus.SUCCESS.getMessage(),
			BaseResponseStatus.SUCCESS.getCode(),
			null
		);
	}
}

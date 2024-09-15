package starbucks3355.starbucksServer.delivery.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.jwt.JwtTokenProvider;
import starbucks3355.starbucksServer.delivery.dto.request.DeliveryAddRequestDto;
import starbucks3355.starbucksServer.delivery.dto.response.DeliveryAllResponseDto;
import starbucks3355.starbucksServer.delivery.dto.response.DeliveryBaseResponseDto;
import starbucks3355.starbucksServer.delivery.service.DeliveryService;
import starbucks3355.starbucksServer.delivery.vo.request.DeliveryAddRequestVo;
import starbucks3355.starbucksServer.delivery.vo.response.DeliveryAllResponseVo;
import starbucks3355.starbucksServer.delivery.vo.response.DeliveryBaseResponseVo;

@RestController
@RequestMapping("/api/v1/delivery")
@Tag(name = "배송지 API", description = "배송지 API")
@RequiredArgsConstructor
@Slf4j
public class DeliveryController {
	private final DeliveryService deliveryService;
	private final JwtTokenProvider jwtTokenProvider;

	//

	@PostMapping("/add")
	@Operation(summary = "배송지 추가", description = "배송지를 추가합니다.")
	public CommonResponseEntity<Void> addDelivery(
		@RequestParam String memberUuid,
		@RequestBody DeliveryAddRequestVo deliveryAddRequestVo) {

		DeliveryAddRequestDto deliveryAddRequestDto = DeliveryAddRequestDto.builder()
			.nickname(deliveryAddRequestVo.getNickname())
			.postNumber(deliveryAddRequestVo.getPostNumber())
			.address(deliveryAddRequestVo.getAddress())
			.detailAddress(deliveryAddRequestVo.getDetailAddress())
			.phone1(deliveryAddRequestVo.getPhone1())
			.phone2(deliveryAddRequestVo.getPhone2())
			.message(deliveryAddRequestVo.getMessage())
			.baseAddress(deliveryAddRequestVo.isBaseAddress())
			.build();

		deliveryService.createDelivery(memberUuid, deliveryAddRequestDto);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@GetMapping("/all")
	@Operation(summary = "배송지 전체 조회", description = "등록된 배송지를 전체 조회합니다.")
	public CommonResponseEntity<List<DeliveryAllResponseVo>> getAllDelivery() {
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			deliveryService.getAllDelivery()
				.stream()
				.map(DeliveryAllResponseDto::toVo)
				.collect(Collectors.toList()));
	}

	// @GetMapping("/base")
	// @Operation(summary = "기본 배송지 조회", description = "등록된 기본 배송지를 조회합니다.")
	// public CommonResponseEntity<DeliveryBaseResponseVo> getBaseDelivery(
	// 	@RequestHeader("Authorization") String accessToken) {
	//
	// 	DeliveryBaseResponseDto deliveryBaseResponseDto = deliveryService.getBaseDelivery(
	// 		jwtTokenProvider.validateAndGetUserUuid(accessToken));
	//
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		DeliveryBaseResponseVo.builder()
	// 			.deliveryId(deliveryBaseResponseDto.getDeliveryId())
	// 			.address(deliveryBaseResponseDto.getAddress())
	// 			.detailAddress(deliveryBaseResponseDto.getDetailAddress())
	// 			.build()
	// 	);
	// }

	// @RequestHeader("Authorization") String authorizationHeader
	// if (authorizationHeader == null) {
	// 	log.error("Authorization header is missing.");
	// 	throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header is missing");
	// }
	//
	// // "Bearer " 부분을 제거하고 토큰만 추출
	// String accessToken = authorizationHeader.replace("Bearer ", "");
	//
	// // JWT 토큰을 검증하고 사용자 UUID 추출
	// String userUuid = jwtTokenProvider.parseUuid(accessToken);

	@GetMapping("/base/{uuid}")
	@Operation(summary = "기본 배송지 조회", description = "등록된 기본 배송지를 조회합니다.")
	public CommonResponseEntity<DeliveryBaseResponseVo> getBaseDelivery(
		@PathVariable String uuid) {

		// UUID로 기본 배송지 정보를 조회
		DeliveryBaseResponseDto deliveryBaseResponseDto = deliveryService.getBaseDelivery(uuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			DeliveryBaseResponseVo.builder()
				.deliveryId(deliveryBaseResponseDto.getDeliveryId())
				.detailAddress(deliveryBaseResponseDto.getDetailAddress())
				.address(deliveryBaseResponseDto.getAddress())
				.build()
		);
	}

}

package starbucks3355.starbucksServer.shipping.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.jwt.JwtTokenProvider;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingMemberRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingPutRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingAllResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;
import starbucks3355.starbucksServer.shipping.service.ShippingService;
import starbucks3355.starbucksServer.shipping.vo.request.ShippingAddRequestVo;
import starbucks3355.starbucksServer.shipping.vo.request.ShippingMemberRequestVo;
import starbucks3355.starbucksServer.shipping.vo.response.ShippingAllResponseVo;
import starbucks3355.starbucksServer.shipping.vo.response.ShippingBaseResponseVo;

@RestController
@RequestMapping("/api/v1/shipping")
@Tag(name = "배송지 API", description = "배송지 API")
@RequiredArgsConstructor
@Slf4j
//@AuthenticationPrincipal

public class ShippingController {
	private final ShippingService shippingService;
	private final JwtTokenProvider jwtTokenProvider;

	//
	@PostMapping("/address")
	@Operation(summary = "회원의 주소", description = "회원의 주소 등록")
	public CommonResponseEntity<Void> enrollAddress(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody ShippingMemberRequestVo shippingMemberRequestVo) {

		ShippingMemberRequestDto shippingMemberRequestDto = ShippingMemberRequestDto.builder()
			.address(shippingMemberRequestVo.getAddress())
			.detailAddress(shippingMemberRequestVo.getDetailAddress())
			.nickName(shippingMemberRequestVo.getNickName())
			.postNumber(shippingMemberRequestVo.getPostNumber())
			.receiver(shippingMemberRequestVo.getReceiver())
			.phone1(shippingMemberRequestVo.getPhone1())
			.phone2(shippingMemberRequestVo.getPhone2())
			.baseAddress(shippingMemberRequestVo.isBaseAddress())
			.message(shippingMemberRequestVo.getMessage())
			.build();

		shippingService.enrollAddress(authUserDetail.getUuid(), shippingMemberRequestDto);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@PostMapping("/add")
	@Operation(summary = "배송지 추가", description = "배송지를 추가합니다.")
	public CommonResponseEntity<Void> addDelivery(
		@RequestParam String memberUuid,
		@RequestBody ShippingAddRequestVo shippingAddRequestVo) {

		ShippingAddRequestDto shippingAddRequestDto = ShippingAddRequestDto.builder()
			.nickname(shippingAddRequestVo.getNickname())
			.postNumber(shippingAddRequestVo.getPostNumber())
			.address(shippingAddRequestVo.getAddress())
			.detailAddress(shippingAddRequestVo.getDetailAddress())
			.phone1(shippingAddRequestVo.getPhone1())
			.phone2(shippingAddRequestVo.getPhone2())
			.message(shippingAddRequestVo.getMessage())
			.baseAddress(shippingAddRequestVo.isBaseAddress())
			.build();

		shippingService.createShipping(memberUuid, shippingAddRequestDto);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@GetMapping("/all")
	@Operation(summary = "배송지 전체 조회", description = "등록된 배송지를 전체 조회합니다.")
	public CommonResponseEntity<List<ShippingAllResponseVo>> getAllShippingAddress() {
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			shippingService.getAllShippingAddress()
				.stream()
				.map(ShippingAllResponseDto::toVo)
				.collect(Collectors.toList()));
	}

	@GetMapping("/base")
	@Operation(summary = "기본 배송지 조회", description = "등록된 기본 배송지를 조회합니다.")
	public CommonResponseEntity<ShippingBaseResponseVo> getBaseDelivery(
		//밑에 @쓰면 AuthUserDetail것을 가져와서 사용 가능
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		//인증된 사용자의 uuid를 가져와서 사용 가능
		String userUuid = authUserDetail.getUuid();
		ShippingBaseResponseDto shippingBaseResponseDto = shippingService.getBaseShippingAddress(userUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			ShippingBaseResponseVo.builder()
				.deliveryId(shippingBaseResponseDto.getDeliveryId())
				.address(shippingBaseResponseDto.getAddress())
				.detailAddress(shippingBaseResponseDto.getDetailAddress())
				.build()
		);
	}

	@PutMapping("/modfiy")
	@Operation(summary = "기본 배송지 수정", description = "등록된 기본 배송지를 수정합니다.")
	public CommonResponseEntity<Void> modifyBaseAddress(
		@RequestBody ShippingPutRequestDto shippingPutRequestDto,
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		shippingService.modifyBaseAddress(shippingPutRequestDto, authUserDetail.getUuid());

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

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

	// @GetMapping("/base/{uuid}")
	// @Operation(summary = "기본 배송지 조회", description = "등록된 기본 배송지를 조회합니다.")
	// public CommonResponseEntity<DeliveryBaseResponseVo> getBaseDelivery(
	// 	@PathVariable String uuid) {
	//
	// 	// UUID로 기본 배송지 정보를 조회
	// 	DeliveryBaseResponseDto deliveryBaseResponseDto = deliveryService.getBaseDelivery(uuid);
	//
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		DeliveryBaseResponseVo.builder()
	// 			.deliveryId(deliveryBaseResponseDto.getDeliveryId())
	// 			.detailAddress(deliveryBaseResponseDto.getDetailAddress())
	// 			.address(deliveryBaseResponseDto.getAddress())
	// 			.build()
	// 	);
	// }

}

package starbucks3355.starbucksServer.shipping.controller;

import static starbucks3355.starbucksServer.common.entity.BaseResponseStatus.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.jwt.JwtTokenProvider;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingModifyRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingListResponseDto;
import starbucks3355.starbucksServer.shipping.service.ShippingService;
import starbucks3355.starbucksServer.shipping.vo.request.ShippingAddRequestVo;
import starbucks3355.starbucksServer.shipping.vo.request.ShippingModifyRequestVo;
import starbucks3355.starbucksServer.shipping.vo.response.ShippingBaseResponseVo;
import starbucks3355.starbucksServer.shipping.vo.response.ShippingListResponseVo;
import starbucks3355.starbucksServer.shipping.vo.response.ShippingOneResponseVo;

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
	// @PostMapping("/address")
	// @Operation(summary = "회원의 주소", description = "회원의 주소 등록")
	// public CommonResponseEntity<Void> enrollAddress(
	// 	@AuthenticationPrincipal AuthUserDetail authUserDetail,
	// 	@RequestBody ShippingMemberRequestVo shippingMemberRequestVo) {
	//
	// 	ShippingMemberRequestDto shippingMemberRequestDto = ShippingMemberRequestDto.builder()
	// 		.address(shippingMemberRequestVo.getAddress())
	// 		.detailAddress(shippingMemberRequestVo.getDetailAddress())
	// 		.nickName(shippingMemberRequestVo.getNickName())
	// 		.postNumber(shippingMemberRequestVo.getPostNumber())
	// 		.receiver(shippingMemberRequestVo.getReceiver())
	// 		.phone1(shippingMemberRequestVo.getPhone1())
	// 		.phone2(shippingMemberRequestVo.getPhone2())
	// 		.baseAddress(shippingMemberRequestVo.isBaseAddress())
	// 		.message(shippingMemberRequestVo.getMessage())
	// 		.build();
	//
	// 	shippingService.enrollAddress(authUserDetail.getUuid(), shippingMemberRequestDto);
	//
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		null);
	// }

	@PostMapping("/add")
	@Operation(summary = "배송지 추가", description = "배송지를 추가합니다.")
	public BaseResponse<Void> addDelivery(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody ShippingAddRequestVo shippingAddRequestVo) {

		ShippingAddRequestDto shippingAddRequestDto = ShippingAddRequestDto.builder()
			.nickname(shippingAddRequestVo.getNickname())
			.postNumber(shippingAddRequestVo.getPostNumber())
			.address(shippingAddRequestVo.getAddress())
			.detailAddress(shippingAddRequestVo.getDetailAddress())
			.phone1(shippingAddRequestVo.getPhone1())
			.phone2(shippingAddRequestVo.getPhone2())
			.message(shippingAddRequestVo.getMessage())
			.receiver(shippingAddRequestVo.getReceiver())
			.baseAddress(shippingAddRequestVo.isBaseAddress())
			.build();

		shippingService.createShipping(authUserDetail.getUuid(), shippingAddRequestDto);

		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			null);

	}

	// @GetMapping("/all")
	// @Operation(summary = "배송지 전체 조회", description = "등록된 배송지를 전체 조회합니다.")
	// public CommonResponseEntity<List<ShippingAllResponseVo>> getAllShippingAddress() {
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		shippingService.getAllShippingAddress()
	// 			.stream()
	// 			.map(ShippingAllResponseDto::toVo)
	// 			.collect(Collectors.toList()));
	// }

	@GetMapping("/base")
	@Operation(summary = "기본 배송지 조회", description = "등록된 기본 배송지를 조회합니다.")
	public BaseResponse<ShippingBaseResponseVo> getBaseDelivery(
		//밑에 @쓰면 AuthUserDetail것을 가져와서 사용 가능
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {

		//인증된 사용자의 uuid를 가져와서 사용 가능
		String userUuid = authUserDetail.getUuid();
		ShippingBaseResponseDto shippingBaseResponseDto = shippingService.getBaseShippingAddress(userUuid);

		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			shippingBaseResponseDto.toVo());
	}

	@PutMapping("/base/{deliveryId}/set-default")
	@Operation(summary = "기본 배송지 변경", description = "기본 배송지를 설정합니다.")
	public BaseResponse<Void> setDefaultDelivery(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable Long deliveryId) {

		shippingService.modifyBaseAddress(authUserDetail.getUuid(), deliveryId);

		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			null);
	}

	@GetMapping("/shipping-list")
	@Operation(summary = "배송지 목록 조회", description = "등록된 배송지 목록을 조회합니다.")
	public BaseResponse<List<ShippingListResponseVo>> getShippingList(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {
		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			shippingService.getShippingList(authUserDetail.getUuid())
				.stream()
				.map(ShippingListResponseDto::toVo)
				.collect(Collectors.toList()));
	}

	@DeleteMapping("/delete/{deliveryId}")
	@Operation(summary = "배송지 삭제", description = "배송지를 삭제합니다.")
	public BaseResponse<Void> deleteDelivery(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable Long deliveryId) {

		shippingService.deleteShipping(authUserDetail.getUuid(), deliveryId);

		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			null);

	}

	@PutMapping("/modify/{deliveryId}")
	@Operation(summary = "배송지 수정", description = "배송지를 수정합니다.")
	public BaseResponse<Void> modifyDelivery(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable Long deliveryId,
		@RequestBody ShippingModifyRequestVo shippingModifyRequestVo) {

		ShippingModifyRequestDto shippingModifyRequestDto = ShippingModifyRequestDto.builder()
			.address(shippingModifyRequestVo.getAddress())
			.detailAddress(shippingModifyRequestVo.getDetailAddress())
			.nickname(shippingModifyRequestVo.getNickname())
			.postNumber(shippingModifyRequestVo.getPostNumber())
			.phone1(shippingModifyRequestVo.getPhone1())
			.phone2(shippingModifyRequestVo.getPhone2())
			.message(shippingModifyRequestVo.getMessage())
			.baseAddress(shippingModifyRequestVo.isBaseAddress())
			.receiver(shippingModifyRequestVo.getReceiver())
			.build();

		shippingService.modifyShipping(authUserDetail.getUuid(), deliveryId, shippingModifyRequestDto);

		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			null);
	}

	@GetMapping("/shipping-only/{deliveryId}")
	@Operation(summary = "배송지 단일 조회", description = "등록된 배송지를 조회합니다.")
	public BaseResponse<ShippingOneResponseVo> getShippingOne(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable Long deliveryId) {

		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			shippingService.getShippingOne(authUserDetail.getUuid(), deliveryId).toVo());
	}

	@PutMapping("/agreeCancel")
	@Operation(summary = "배송지 동의 약관", description = "배송지 동의 약관")
	public BaseResponse<Boolean> agreeShippingCancel(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {
		boolean updateAgreeStatus = shippingService.agreeShippingCancel(authUserDetail.getUuid());
		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			updateAgreeStatus);
	}

	@GetMapping("/agreeStatus")
	@Operation(summary = "true-false", description = "true-false")
	public BaseResponse<Boolean> getAgreeStatus(
		@AuthenticationPrincipal AuthUserDetail authUserDetail) {
		boolean agreeStatus = shippingService.getAgreeStatus(authUserDetail.getUuid());
		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			agreeStatus);
	}
}

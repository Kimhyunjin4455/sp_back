package starbucks3355.starbucksServer.domainOrders.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestReadyDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseGetDto;
import starbucks3355.starbucksServer.domainOrders.service.KakaoService;

@RestController
@RequestMapping("/api/v1/kakao")
@RequiredArgsConstructor
@Tag(name = "카카오페이", description = "카카오페이 결제 API")
public class KakaoPayController {
	private final KakaoService kakaoService;

	@PostMapping("/ready")
	@Operation(summary = "카카오페이 결제 준비", description = "카카오페이 결제 준비 API를 호출합니다.")
	public CommonResponseEntity<KakaoResponseGetDto> KakaoPayReady(
		@RequestBody KakaoRequestReadyDto kakaoRequestReadyDto) {
		KakaoResponseGetDto response = kakaoService.getKakaoPayReady(kakaoRequestReadyDto);
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			response);

	}

	@PostMapping("/approve")
	@Operation(summary = "카카오페이 결제 승인", description = "카카오페이 결제 승인 API를 호출합니다.")
	public CommonResponseEntity<KakaoResponseApproveDto> KakaoPayApprove(
		@RequestBody KakaoRequestApproveDto kakaoRequestApproveDto) {
		KakaoResponseApproveDto response = kakaoService.getKakaoPayApprove(kakaoRequestApproveDto);
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			response);
	}
}

package starbucks3355.starbucksServer.domainOrders.controller;

import static starbucks3355.starbucksServer.common.entity.BaseResponseStatus.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestReadyDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseReadyDto;
import starbucks3355.starbucksServer.domainOrders.service.KakaoService;

@RestController
@RequestMapping("/api/v1/kakao")
@RequiredArgsConstructor
@Tag(name = "카카오페이", description = "카카오페이 결제 API")
public class KakaoPayController {
	private final KakaoService kakaoService;

	@PostMapping("/ready")
	@Operation(summary = "카카오페이 결제 준비", description = "카카오페이 결제 준비 API를 호출합니다.")
	public BaseResponse<KakaoResponseReadyDto> KakaoPayReady(
		@RequestBody KakaoRequestReadyDto kakaoRequestReadyDto) {
		KakaoResponseReadyDto response = kakaoService.getKakaoPayReady(kakaoRequestReadyDto);
		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			response);

	}

	//pgToken 얻기
	@PostMapping("/success")
	@Operation(summary = "카카오페이 결제 승인", description = "카카오페이 결제 승인 API를 호출합니다.")
	public BaseResponse<KakaoResponseApproveDto> getPgToken(
		// 결제 승인할 때 필수적으로 확인이 필요한 파라미터들
		@RequestParam("pg_token") String pgToken,
		@RequestParam("tid") String tid,
		@RequestParam("partner_order_id") String partner_order_id,
		@RequestParam("partner_user_id") String partner_user_id) {
		//@RequestParam("pg_token") String pgToken
		KakaoRequestApproveDto kakaoRequestApproveDto = KakaoRequestApproveDto.builder()
			.tid(tid)
			.pgToken(pgToken)
			.partner_order_id(partner_order_id)
			.partner_user_id(partner_user_id)
			.build();

		KakaoResponseApproveDto response = kakaoService.getKakaoPayApprove(kakaoRequestApproveDto);

		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			response);
	}

}

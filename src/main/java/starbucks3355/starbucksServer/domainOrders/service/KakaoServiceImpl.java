package starbucks3355.starbucksServer.domainOrders.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.config.KakaoProperties;
import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestReadyDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseGetDto;

@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService {

	// KakaoProperties 클래스를 주입받아 사용,
	private final KakaoProperties kakaoProperties;

	@Override
	// 카카오페이의 결제 준비
	public KakaoResponseGetDto getKakaoPayReady(KakaoRequestReadyDto kakaoRequestReadyDto) {
		// HttpEntity 객체 생성: HTTP 요청(body)와 HTTP 헤더를 포함한 엔티티를 생성
		// getReadyParameters(kakaoRequestDto)는 요청에 필요한 파라미터를 설정하며,
		// getHeaders()는 Authorization 등의 HTTP 헤더를 설정하는 메소드.
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(
			this.getReadyParameters(kakaoRequestReadyDto), this.getHeaders());
		try {
			// RestTemplate 객체 생성: 스프링에서 제공하는 HTTP 요청을 보낼 수 있는 유틸리티 클래스.
			// 이를 사용해 카카오페이 API에 POST 요청을 보냄.
			RestTemplate restTemplate = new RestTemplate();

			// 카카오페이 결제 준비 API에 POST 요청을 보냄.

			KakaoResponseGetDto response = restTemplate.postForObject(
				"https://open-api.kakaopay.com/v1/payment/ready", //카카오 api 호출하여 결제 준비를 처리함
				requestEntity, // 두 번째 인자는 실제 요청 데이터(requestEntity)로, 요청 본문과 헤더가 포함되어 있음.
				KakaoResponseGetDto.class); // 카카오페이 API 응답 데이터를 해당 DTO 객체로 변환하여 반환함.

			return response; // 결제 준비 API의 응답을 반환.
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(e.getStatusCode(), e.getMessage());
		}
	}

	//HTTP 헤더 생성
	private HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		// Authorization 헤더 값을 생성: 카카오페이 API 호출을 위한 인증 키를 만듭니다.
		// "KakaoAK"라는 문자열과, kakaoProperties 객체에서 가져온 adminKey를 결합해 인증 토큰을 만듭니다.
		String auth = "SECRET_KEY " + kakaoProperties.getSecretKey();

		// Authorization 헤더 설정: "Authorization" 헤더에 방금 만든 auth 문자열을 추가합니다.
		// 이 헤더는 서버에 클라이언트의 인증 정보(카카오페이 adminKey)를 전달합니다.
		httpHeaders.set("Authorization", auth);
		httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		return httpHeaders;
	}

	// 결제 준비 요청에 필요한 파라미터 설정
	private Map<String, String> getReadyParameters(KakaoRequestReadyDto kakaoRequestReadyDto) {
		Map<String, String> parameters = new HashMap<>();

		parameters.put("cid", kakaoProperties.getCid());
		parameters.put("partner_order_id", kakaoRequestReadyDto.getPartnerOrderId());
		parameters.put("partner_user_id", kakaoRequestReadyDto.getPartnerUserId());
		parameters.put("item_name", kakaoRequestReadyDto.getItemName());
		parameters.put("quantity", kakaoRequestReadyDto.getQuantity().toString());
		parameters.put("total_amount", kakaoRequestReadyDto.getTotalAmount().toString());
		parameters.put("tax_free_amount", kakaoRequestReadyDto.getTaxFreeAmount().toString());
		parameters.put("approval_url", kakaoRequestReadyDto.getApprovalUrl());
		parameters.put("cancel_url", kakaoRequestReadyDto.getCancelUrl());
		parameters.put("fail_url", kakaoRequestReadyDto.getFailUrl());

		return parameters;
	}

	// 결제 승인 api를 호출하여 결제 승인을 처리하는 로직
	private Map<String, String> getApproveParameters(KakaoRequestApproveDto kakaoRequestApproveDto) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("cid", kakaoProperties.getCid());
		parameters.put("tid", kakaoRequestApproveDto.getTid());
		parameters.put("partner_user_id", kakaoRequestApproveDto.getPartnerUserId());
		parameters.put("partner_order_id", kakaoRequestApproveDto.getPartnerOrderId());
		parameters.put("pg_token", kakaoRequestApproveDto.getPgToken());
		return parameters;
	}

	public KakaoResponseApproveDto getKakaoPayApprove(KakaoRequestApproveDto kakaoRequestApproveDto) {
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(
			this.getApproveParameters(kakaoRequestApproveDto), this.getHeaders());
		try {
			RestTemplate restTemplate = new RestTemplate();

			KakaoResponseApproveDto response = restTemplate.postForObject(
				"https://open-api.kakaopay.com/v1/payment/approve",
				requestEntity,
				KakaoResponseApproveDto.class);

			return response;
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(e.getStatusCode(), e.getMessage());
		}
	}
}

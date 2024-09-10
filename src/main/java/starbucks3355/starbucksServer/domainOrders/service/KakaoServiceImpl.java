package starbucks3355.starbucksServer.domainOrders.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.config.KakaoProperties;
import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestGetDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseGetDto;

@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService {

	// KakaoProperties 클래스를 주입받아 사용,
	private final KakaoProperties kakaoProperties;

	@Override
	// 카카오페이의 결제 준비
	public KakaoResponseGetDto getKakaoPayReady(KakaoRequestGetDto kakaoRequestGetDto) {
		// HttpEntity 객체 생성: HTTP 요청(body)와 HTTP 헤더를 포함한 엔티티를 생성
		// getReadyParameters(kakaoRequestDto)는 요청에 필요한 파라미터를 설정하며,
		// getHeaders()는 Authorization 등의 HTTP 헤더를 설정하는 메소드.
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(
			this.getReadyParameters(kakaoRequestGetDto), this.getHeaders());
		try {
			// RestTemplate 객체 생성: 스프링에서 제공하는 HTTP 요청을 보낼 수 있는 유틸리티 클래스.
			// 이를 사용해 카카오페이 API에 POST 요청을 보냄.
			RestTemplate restTemplate = new RestTemplate();

			// 카카오페이 결제 준비 API에 POST 요청을 보냄.

			KakaoResponseGetDto response = restTemplate.postForObject(
				"https://kapi.kakao.com/v1/payment/ready", //카카오 api 호출하여 결제 준비를 처리함
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
		String auth = "KakaoAk" + kakaoProperties.getAdminKey();

		// Authorization 헤더 설정: "Authorization" 헤더에 방금 만든 auth 문자열을 추가합니다.
		// 이 헤더는 서버에 클라이언트의 인증 정보(카카오페이 adminKey)를 전달합니다.
		httpHeaders.set("Authorization", auth);

		httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		return httpHeaders;
	}

	// 결제 준비 요청에 필요한 파라미터 설정
	private MultiValueMap<String, String> getReadyParameters(KakaoRequestGetDto kakaoRequestGetDto) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

		parameters.add("cid", kakaoProperties.getCid());
		parameters.add("partner_order_id", kakaoRequestGetDto.getPartnerOrderId());
		parameters.add("item_name", kakaoRequestGetDto.getItemName());
		parameters.add("quantity", kakaoRequestGetDto.getQuantity().toString());
		parameters.add("total_amount", kakaoRequestGetDto.getTotalAmount().toString());
		parameters.add("tax_free_amount", kakaoRequestGetDto.getTaxFreeAmount().toString());
		parameters.add("approval_url", kakaoRequestGetDto.getApprovalUrl());
		parameters.add("cancel_url", kakaoRequestGetDto.getCancelUrl());
		parameters.add("fail_url", kakaoRequestGetDto.getFailUrl());

		return parameters;
	}

	// 결제 승인 api를 호출하여 결제 승인을 처리하는 로직
	private MultiValueMap<String, String> getApproveParameters(KakaoRequestGetDto kakaoRequestGetDto) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

		parameters.add("cid", kakaoProperties.getCid());
		parameters.add("tid", kakaoRequestGetDto.getTid());
		parameters.add("partner_order_id", kakaoRequestGetDto.getPartnerOrderId());
		parameters.add("pg_token", kakaoRequestGetDto.getPgToken());

		return parameters;
	}

	public KakaoResponseApproveDto getKakaoPayApprove(KakaoRequestGetDto kakaoRequestGetDto) {
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(
			this.getApproveParameters(kakaoRequestGetDto), this.getHeaders());
		try {
			RestTemplate restTemplate = new RestTemplate();

			KakaoResponseApproveDto response = restTemplate.postForObject(
				"https://kapi.kakao.com/v1/payment/approve",
				requestEntity,
				KakaoResponseApproveDto.class);

			return response;
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(e.getStatusCode(), e.getMessage());
		}
	}
}

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
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseReadyDto;
import starbucks3355.starbucksServer.domainOrders.entity.KakaoPay;
import starbucks3355.starbucksServer.domainOrders.repository.KakaoRepository;
import starbucks3355.starbucksServer.shipping.repository.ShippingRepository;

@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService {

	// KakaoProperties 클래스를 주입받아 사용,
	private final KakaoProperties kakaoProperties;
	private final KakaoRepository kakaoRepository;
	private final ShippingRepository shippingRepository;

	@Override
	// 카카오페이의 결제 준비
	public KakaoResponseReadyDto getKakaoPayReady(KakaoRequestReadyDto kakaoRequestReadyDto) {
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

			KakaoResponseReadyDto response = restTemplate.postForObject(
				"https://open-api.kakaopay.com/online/v1/payment/ready", //카카오 api 호출하여 결제 준비를 처리함
				requestEntity, // 두 번째 인자는 실제 요청 데이터(requestEntity)로, 요청 본문과 헤더가 포함되어 있음.
				KakaoResponseReadyDto.class); // 카카오페이 API 응답 데이터를 해당 DTO 객체로 변환하여 반환함.
			System.out.println(response);

			// KakaoPay kakaoPay = KakaoPay.builder()
			// 	.cid(kakaoRequestReadyDto.getCid())
			// 	.partnerOrderId(kakaoRequestReadyDto.getPartnerOrderId())
			// 	.partnerUserId(kakaoRequestReadyDto.getPartnerUserId())
			// 	.itemName(kakaoRequestReadyDto.getItemName())
			// 	.quantity(kakaoRequestReadyDto.getQuantity())
			// 	.totalAmount(kakaoRequestReadyDto.getTotalAmount())
			// 	.taxFreeAmount(kakaoRequestReadyDto.getTaxFreeAmount())
			// 	.build();
			//
			// kakaoRepository.save(kakaoPay);

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
		httpHeaders.set("Content-type", "application/json");
		return httpHeaders;
	}

	// 결제 준비 요청에 필요한 파라미터 설정
	private Map<String, String> getReadyParameters(KakaoRequestReadyDto kakaoRequestReadyDto) {
		Map<String, String> parameters = new HashMap<>();

		parameters.put("cid", kakaoProperties.getCid());
		parameters.put("partner_order_id", kakaoRequestReadyDto.getPartner_order_id());
		parameters.put("partner_user_id", kakaoRequestReadyDto.getPartner_user_id());
		parameters.put("item_name", kakaoRequestReadyDto.getItem_name());
		parameters.put("quantity", kakaoRequestReadyDto.getQuantity().toString());
		parameters.put("total_amount", kakaoRequestReadyDto.getTotal_amount().toString());
		parameters.put("tax_free_amount", kakaoRequestReadyDto.getTax_free_amount().toString());
		//url은 Redirect URL로 결제 완료 후 이동할 URL을 설정합니다.
		parameters.put("approval_url",
			// "http://localhost:8080/swagger-ui/index.html?urls.primaryName=%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4#/%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4/getPgToken");
			// "http://3.38.239.2:8080/swagger-ui/index.html?urls.primaryName=%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4#/%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4/getPgToken");
			// "http://localhost:3000/paid");
			"https://3355-five.vercel.app/paid?urls.primaryName=%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4#/%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4/getPgToken");
		parameters.put("cancel_url", "http://localhost:8080/cancel");
		parameters.put("fail_url", "http://localhost:8080/fail");
		return parameters;
	}

	// 결제 승인 api를 호출하여 결제 승인을 처리하는 로직
	private Map<String, String> getApproveParameters(KakaoRequestApproveDto kakaoRequestApproveDto) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("cid", kakaoProperties.getCid());
		parameters.put("tid", kakaoRequestApproveDto.getTid());
		parameters.put("partner_user_id", kakaoRequestApproveDto.getPartner_user_id());
		parameters.put("partner_order_id", kakaoRequestApproveDto.getPartner_order_id());
		parameters.put("pg_token", kakaoRequestApproveDto.getPgToken());
		return parameters;
	}

	public KakaoResponseApproveDto getKakaoPayApprove(KakaoRequestApproveDto kakaoRequestApproveDto) {
		HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(
			this.getApproveParameters(kakaoRequestApproveDto), this.getHeaders());
		try {
			RestTemplate restTemplate = new RestTemplate();

			KakaoResponseApproveDto response = restTemplate.postForObject(
				"https://open-api.kakaopay.com/online/v1/payment/approve",
				requestEntity,
				KakaoResponseApproveDto.class);

			KakaoPay kakaoPay = KakaoPay.builder()
				.cid(kakaoProperties.getCid())
				.partner_order_id(kakaoRequestApproveDto.getPartner_order_id())
				.partner_user_id(kakaoRequestApproveDto.getPartner_user_id())
				.item_Name(response.getItem_name())
				.quantity(response.getQuantity())
				.total_amount(response.getAmount().getTotal())
				.tax_free_amount(response.getTax_free_amount() != null ? response.getTax_free_amount() : 0)
				.approved_at(response.getApproved_at())// null 체크 후 기본값 설정
				.created_at(response.getCreated_at())
				.build();
			kakaoRepository.save(kakaoPay);

			return response;
		} catch (HttpClientErrorException e) {
			throw new HttpClientErrorException(e.getStatusCode(), e.getMessage());
		}
	}
}

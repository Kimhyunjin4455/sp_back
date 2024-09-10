package starbucks3355.starbucksServer.domainOrders.service;

import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestGetDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseGetDto;

public interface KakaoService {
	KakaoResponseGetDto getKakaoPayReady(KakaoRequestGetDto kakaoRequestGetDto);

	KakaoResponseApproveDto getKakaoPayApprove(KakaoRequestGetDto kakaoRequestGetDto);
}

package starbucks3355.starbucksServer.domainOrders.service;

import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestReadyDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseApproveDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseReadyDto;

public interface KakaoService {
	KakaoResponseReadyDto getKakaoPayReady(KakaoRequestReadyDto kakaoRequestReadyDto);

	KakaoResponseApproveDto getKakaoPayApprove(KakaoRequestApproveDto kakaoRequestApproveDto);

	//KakaoRequestCartDto createKakaoRequestReadyDto(List<WishList> wishLists, String orderId, String userId);
}

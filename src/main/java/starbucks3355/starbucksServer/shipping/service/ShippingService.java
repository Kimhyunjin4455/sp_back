package starbucks3355.starbucksServer.shipping.service;

import java.util.List;

import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingMemberRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingAllResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingListResponseDto;

public interface ShippingService {
	//void createAddDelivery(DeliveryAddRequestDto deliveryAddRequestDto);

	// 모든 배송지 조회
	List<ShippingAllResponseDto> getAllShippingAddress();

	// 기본 배송지 조회
	ShippingBaseResponseDto getBaseShippingAddress(String uuid);

	// 배송지 추가(생성)
	void createShipping(String memberUuid, ShippingAddRequestDto shippingAddRequestDto);

	// 기본 배송지 변경
	void modifyBaseAddress(String uuid, Long deliveryId);

	// 회원의 주소 등록
	void enrollAddress(String uuid, ShippingMemberRequestDto shippingMemberRequestDto);

	// 배송지 목록 조회
	List<ShippingListResponseDto> getShippingList(String uuid);

	// 배송지 삭제
	void deleteShipping(String uuid, Long deliveryId);
}

package starbucks3355.starbucksServer.shipping.service;

import java.util.List;

import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingMemberRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingAllResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingListResponseDto;

public interface ShippingService {
	//void createAddDelivery(DeliveryAddRequestDto deliveryAddRequestDto);

	List<ShippingAllResponseDto> getAllShippingAddress();

	ShippingBaseResponseDto getBaseShippingAddress(String uuid);

	void createShipping(String memberUuid, ShippingAddRequestDto shippingAddRequestDto);

	void modifyBaseAddress(String uuid, Long deliveryId);

	void enrollAddress(String uuid, ShippingMemberRequestDto shippingMemberRequestDto);

	List<ShippingListResponseDto> getShippingList(String uuid);

}

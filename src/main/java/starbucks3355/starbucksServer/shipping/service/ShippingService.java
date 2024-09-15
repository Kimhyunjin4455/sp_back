package starbucks3355.starbucksServer.shipping.service;

import java.util.List;

import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingAllResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;

public interface ShippingService {
	//void createAddDelivery(DeliveryAddRequestDto deliveryAddRequestDto);

	List<ShippingAllResponseDto> getAllDelivery();

	ShippingBaseResponseDto getBaseDelivery(String uuid);

	void createDelivery(String memberUuid, ShippingAddRequestDto shippingAddRequestDto);

}

package starbucks3355.starbucksServer.delivery.service;

import java.util.List;

import starbucks3355.starbucksServer.delivery.dto.request.DeliveryAddRequestDto;
import starbucks3355.starbucksServer.delivery.dto.response.DeliveryAllResponseDto;
import starbucks3355.starbucksServer.delivery.dto.response.DeliveryBaseResponseDto;

public interface DeliveryService {
	void createAddDelivery(DeliveryAddRequestDto deliveryAddRequestDto);

	List<DeliveryAllResponseDto> getAllDelivery();

	DeliveryBaseResponseDto getBaseDelivery(String uuid);

}

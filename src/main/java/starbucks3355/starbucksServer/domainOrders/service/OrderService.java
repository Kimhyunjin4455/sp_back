package starbucks3355.starbucksServer.domainOrders.service;

import starbucks3355.starbucksServer.domainOrders.dto.request.OrderRequestDto;

public interface OrderService {

	//주문 생성
	public void createOrder(OrderRequestDto orderRequestDto);
}

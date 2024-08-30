package starbucks3355.starbucksServer.domainOrders.service;

import java.util.List;

import starbucks3355.starbucksServer.domainOrders.dto.request.OrderRequestDto;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;

public interface OrderService {

	//주문 생성
	public void createOrder(OrderRequestDto orderRequestDto);

	//주문 목록 조회
	public List<Orders> getAllOrders();
}

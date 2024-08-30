package starbucks3355.starbucksServer.domainOrders.service;

import java.util.List;
import java.util.UUID;

import starbucks3355.starbucksServer.domainOrders.dto.request.OrderRequestDto;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;

public interface OrderService {

	//주문 생성
	public void createOrder(OrderRequestDto orderRequestDto);

	//주문 목록 조회
	public List<Orders> getAllOrders();

	//주문 상태 변경
	public void updateOrderStatus(UUID uuId, OrderStatus newOrderStatus);
}

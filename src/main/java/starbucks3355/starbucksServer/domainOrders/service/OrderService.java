package starbucks3355.starbucksServer.domainOrders.service;

import java.util.List;

import starbucks3355.starbucksServer.domainOrders.dto.request.OrderRequestDto;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderUpdateRequestDto;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;
import starbucks3355.starbucksServer.domainOrders.vo.request.OrderUpdateRequestVo;

public interface OrderService {

	//주문 생성
	public void createOrder(OrderRequestDto orderRequestDto);

	//주문 목록 조회
	public List<Orders> getAllOrders();

	//주문 상태 변경
	public void updateOrderStatus(OrderUpdateRequestDto orderUpdateRequestDto);
}

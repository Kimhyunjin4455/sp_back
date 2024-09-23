package starbucks3355.starbucksServer.domainOrders.service;

import starbucks3355.starbucksServer.domainOrders.dto.request.OrderCreateRequestDto;

public interface OrderService {

	//주문 생성

	void createOrder(OrderCreateRequestDto orderCreateRequestDto, String userId);

	//OrderCreateRequestDto createKakaoRequestReadyDto(List<OrderCreateRequestVo> orderCreateRequestVoList,
	//String orderId, String uuid);

	//String prepareKakaoPay(OrderCreateRequestDto orderCreateRequestDto);

	//주문 목록 조회
	//public List<Orders> getAllOrders();

	//주문 상태 변경
	//public void updateOrderStatus(OrderUpdateRequestDto orderUpdateRequestDto);

	// 주문 생성 및 카카오페이 결제 준비 로직 통합
	//String prepareOrderWithKakaoPay(List<OrderCreateRequestVo> orderCreateRequestVoList, String uuid);

}

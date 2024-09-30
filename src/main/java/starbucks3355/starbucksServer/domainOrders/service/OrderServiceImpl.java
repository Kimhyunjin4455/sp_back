package starbucks3355.starbucksServer.domainOrders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderCreateRequestDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.OrderResponseDto;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;
import starbucks3355.starbucksServer.domainOrders.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	//private final KakaoService kakaoService;

	// 주문 생성
	@Override
	public void createOrder(OrderCreateRequestDto orderCreateRequestDto, String userId) {

		// 주문 생성
		Orders orderCreateRequestDtos = orderCreateRequestDto.toEntity(orderCreateRequestDto, userId);

		orderRepository.save(orderCreateRequestDtos);

	}

	// 주문 요약 정보 계산

	//주문 목록 조회
	public List<OrderResponseDto> getAllOrders(String userId) {
		List<Orders> ordersList = orderRepository.findOrderResponseVoListByUserId(userId);
		return ordersList.stream()
			.map(order -> OrderResponseDto.builder()
				.id(order.getId())
				.productQuantity(order.getProductQuantity())
				.totalAmount(order.getTotalAmount())
				.address(order.getAddress())
				.detailAddress(order.getDetailAddress())
				.phone1(order.getPhone1())
				.orderStatus(order.getOrderStatus())
				.userName(order.getUserName())
				.build())
			.toList();
	}

	// 단일 주문 조회
	@Override
	public OrderResponseDto getOneOrder(String userId, String orderId) {
		Orders orders = orderRepository.findByUserIdAndOrderId(userId, orderId);
		return OrderResponseDto.builder()
			.id(orders.getId())
			.productQuantity(orders.getProductQuantity())
			.totalAmount(orders.getTotalAmount())
			.address(orders.getAddress())
			.detailAddress(orders.getDetailAddress())
			.phone1(orders.getPhone1())
			.orderStatus(orders.getOrderStatus())
			.userName(orders.getUserName())
			.build();
	}

	// // 주문 상태 변경  -> 주문 취소 api
	@Transactional
	public void cancelOrderStatus(String userId, String orderId) {
		Orders order = orderRepository.findByUserIdAndOrderId(userId, orderId);
		// 주문 상태 변경
		//builder를 통한 상태 업데이트
		orderRepository.save(Orders.builder()
			.id(order.getId())
			.totalAmount(order.getTotalAmount())
			.userName(order.getUserName())
			.orderId(order.getOrderId())
			.userId(order.getUserId())
			.productQuantity(order.getProductQuantity())
			.phone1(order.getPhone1())
			.address(order.getAddress())
			.detailAddress(order.getDetailAddress())
			.productUuid(order.getProductUuid())
			.orderStatus(OrderStatus.CANCEL)
			.build());

	}
}


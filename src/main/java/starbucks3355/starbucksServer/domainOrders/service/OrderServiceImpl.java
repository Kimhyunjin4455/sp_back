package starbucks3355.starbucksServer.domainOrders.service;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderRequestDto;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderUpdateRequestDto;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;
import starbucks3355.starbucksServer.domainOrders.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;

	// 주문 생성
	@Override
	public void createOrder(OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		orderRepository.save(orderRequestDto.toEntity(orderRequestDto));

		// Order order = orderRequestDto.toEntity();
		// orderRepository.save(order);
	}

	// 주문 목록 조회
	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	// 주문 상태 변경
	@Transactional
	public void updateOrderStatus(OrderUpdateRequestDto orderUpdateRequestDto) {
		log.info("서비스-uuid:" + orderUpdateRequestDto.getUuid());
		Orders order = orderRepository.findByUuid(orderUpdateRequestDto.getUuid())
			.orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다."));
		// 주문 상태 변경
		//builder를 통한 상태 업데이트
		orderRepository.save(Orders.builder()
				.id(order.getId())
				.orderDate(order.getOrderDate())
				.totalAmount(order.getTotalAmount())
				.uuid(order.getUuid())
				.userName(order.getUserName())
				.userPhoneNumber(order.getUserPhoneNumber())
				.userAddress(order.getUserAddress())
				.orderStatus(OrderStatus.CANCEL)
				.build());
	}
}


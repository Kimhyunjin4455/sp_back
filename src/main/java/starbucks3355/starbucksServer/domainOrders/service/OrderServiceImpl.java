package starbucks3355.starbucksServer.domainOrders.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderRequestDto;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;
import starbucks3355.starbucksServer.domainOrders.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;

	@Override
	@Transactional
	public void createOrder(UUID uuid, OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		orderRepository.save(orderRequestDto.toEntity(orderRequestDto));

		// Order order = orderRequestDto.toEntity();
		// orderRepository.save(order);
	}

	@Transactional
	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	@Transactional(readOnly = true)
	public void updateOrderStatus(UUID uuid, OrderStatus newOrderStatus) {
		Orders order = orderRepository.findByuuid(uuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. id=" + uuid));
		order.updateOrderStatus(newOrderStatus);
	}
}


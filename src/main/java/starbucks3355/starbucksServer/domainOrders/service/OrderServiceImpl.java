package starbucks3355.starbucksServer.domainOrders.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderRequestDto;
import starbucks3355.starbucksServer.domainOrders.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;

	@Override
	@Transactional
	public void createOrder(OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		orderRepository.save(orderRequestDto.toEntity(orderRequestDto));

		// Order order = orderRequestDto.toEntity();
		// orderRepository.save(order);
	}
}


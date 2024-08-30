package starbucks3355.starbucksServer.domainOrders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderRequestDto;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;
import starbucks3355.starbucksServer.domainOrders.service.OrderService;
import starbucks3355.starbucksServer.domainOrders.vo.request.OrderRequestVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

	private final OrderService orderService;

	//주문 생성 ModelMapper 미사용, 이거 다음 Controller에서  생성할 듯
	// OrderRequestVo를 OrderRequestDto로 변환
	@PostMapping
	public ResponseEntity<Void> createOrders(OrderRequestVo orderRequestVo) {
		OrderRequestDto orderRequestDto = new OrderRequestDto(
			orderRequestVo.getOrderDate(),
			orderRequestVo.getTotalAmount(),
			orderRequestVo.getUuid(),
			orderRequestVo.getUserName(),
			orderRequestVo.getUserPhoneNumber(),
			orderRequestVo.getUserAddress()

		);
		// dto 값을 service로 넘겨줌
		// service를 이용해서 db에 dto 값을 저장하기 위함
		orderService.createOrder(orderRequestDto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	//주문 목록 조회
	@PostMapping
	public ResponseEntity<List<Orders>> getAllOrders() {
		List<Orders> orders = orderService.getOrders();
		return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
	}
}

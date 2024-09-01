package starbucks3355.starbucksServer.domainOrders.controller;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderRequestDto;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;
import starbucks3355.starbucksServer.domainOrders.service.OrderService;
import starbucks3355.starbucksServer.domainOrders.vo.request.OrderRequestVo;
import starbucks3355.starbucksServer.domainOrders.vo.response.OrderResponseVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders", description = "주문 API")
public class OrderController {

	private final OrderService orderService;

	//주문 생성 ModelMapper 미사용, 이거 다음 Controller에서  생성할 듯
	// OrderRequestVo를 OrderRequestDto로 변환
	@PostMapping
	@Operation(summary = "주문 생성")
	public ResponseEntity<Void> createOrders(@RequestBody OrderRequestVo orderRequestVo) {

		OrderRequestDto orderRequestDto = new OrderRequestDto(
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
	@GetMapping("/list")
	@Operation(summary = "주문 목록 조회")
	public ResponseEntity<List<OrderResponseVo>> getAllOrders() {
		// 서비스에서 목록 가져오기
		List<Orders> ordersList = orderService.getAllOrders();
		// 주문 목록을 OrderResponseVo로 변환
		List<OrderResponseVo> orderResponseVoList = ordersList.stream()
			.map(order -> OrderResponseVo.builder()
				.orderDate(order.getOrderDate())
				.totalAmount(order.getTotalAmount())
				.uuId(order.getUuid())
				.userName(order.getUserName())
				.userPhoneNumber(order.getUserPhoneNumber())
				.userAddress(order.getUserAddress())
				.build())
			.collect(Collectors.toList());
		// 변환된 목록을 반환
		return new ResponseEntity<List<OrderResponseVo>>(
			orderResponseVoList,
			HttpStatus.OK);
	}

	// 주문 상태 변경
	@PutMapping("/modify/status")
	@Operation(summary = "주문 상태 변경")
	public ResponseEntity<String> updateOrderStatus(@RequestBody OrderRequestVo orderRequestVo) {
		orderService.updateOrderStatus(orderRequestVo.getUuid(), orderRequestVo.getOrderStatus());
		return new ResponseEntity<String>("주문 상태 변경 완료", HttpStatus.OK);
	}

}

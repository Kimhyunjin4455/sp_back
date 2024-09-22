package starbucks3355.starbucksServer.domainOrders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.domainOrders.service.KakaoService;
import starbucks3355.starbucksServer.domainOrders.service.OrderService;
import starbucks3355.starbucksServer.domainOrders.vo.request.OrderCreateRequestVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders", description = "주문 API")
@Slf4j
public class OrderController {

	private final OrderService orderService;
	private final KakaoService kakaoService;

	//주문 생성 ModelMapper 미사용, 이거 다음 Controller에서  생성할 듯
	// OrderRequestVo를 OrderRequestDto로 변환
	@PostMapping("/createOrder")
	@Operation(summary = "주문 생성")
	public CommonResponseEntity<String> createOrders(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody List<OrderCreateRequestVo> orderCreateRequestVoList) {

		String redirectUrl = orderService.prepareOrderWithKakaoPay(orderCreateRequestVoList, authUserDetail.getUuid());

		// dto 값을 service로 넘겨줌
		// service를 이용해서 db에 dto 값을 저장하기 위함
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			redirectUrl);

	}

	//주문 목록 조회
	// @GetMapping("/list")
	// @Operation(summary = "주문 목록 조회")
	// public ResponseEntity<List<OrderResponseVo>> getAllOrders() {
	// 	// 서비스에서 목록 가져오기
	// 	List<Orders> ordersList = orderService.getAllOrders();
	// 	// 주문 목록을 OrderResponseVo로 변환
	// 	List<OrderResponseVo> orderResponseVoList = ordersList.stream()
	// 		.map(order -> OrderResponseVo.builder()
	// 			.orderDate(order.getOrderDate())
	// 			.totalAmount(order.getTotalAmount())
	// 			.uuId(order.getUuid())
	// 			.userName(order.getUserName())
	// 			.userPhoneNumber(order.getUserPhoneNumber())
	// 			.userAddress(order.getUserAddress())
	// 			.build())
	// 		.collect(Collectors.toList());
	// 	// 변환된 목록을 반환
	// 	return new ResponseEntity<List<OrderResponseVo>>(
	// 		orderResponseVoList,
	// 		HttpStatus.OK);
	// }

	// 주문 상태 변경
	// @PutMapping("/modify/status")
	// @Operation(summary = "주문 상태 변경")
	// public ResponseEntity<Void> updateOrderStatus(@RequestBody OrderUpdateRequestVo orderUpdateRequestVo) {
	//
	// 	OrderUpdateRequestDto orderUpdateRequestDto = OrderUpdateRequestDto.builder()
	// 		.uuid(orderUpdateRequestVo.getUuid())
	// 		.build();
	//
	// 	log.info(orderUpdateRequestDto.getUuid().toString());
	// 	orderService.updateOrderStatus(orderUpdateRequestDto);
	// 	return new ResponseEntity<Void>(HttpStatus.OK);
	// }

}

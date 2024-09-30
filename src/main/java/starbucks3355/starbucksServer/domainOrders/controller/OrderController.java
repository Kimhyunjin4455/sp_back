package starbucks3355.starbucksServer.domainOrders.controller;

import static starbucks3355.starbucksServer.common.entity.BaseResponseStatus.*;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderCreateRequestDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.OrderResponseDto;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;
import starbucks3355.starbucksServer.domainOrders.service.OrderService;
import starbucks3355.starbucksServer.domainOrders.vo.request.OrderCreateRequestVo;
import starbucks3355.starbucksServer.domainOrders.vo.response.OrderResponseVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name = "Orders", description = "주문 API")
@Slf4j
public class OrderController {

	private final OrderService orderService;

	//주문 생성 ModelMapper 미사용, 이거 다음 Controller에서  생성할 듯
	// OrderRequestVo를 OrderRequestDto로 변환
	@PostMapping("/createOrder")
	@Operation(summary = "주문 생성", description = "주문 생성")
	public BaseResponse<Void> createOrders(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@RequestBody OrderCreateRequestVo orderCreateRequestVo) {

		OrderCreateRequestDto orderCreateRequestDto = OrderCreateRequestDto.builder()
			.orderId(UUID.randomUUID().toString()) // 백에서 orderId uuid로 생성
			.productUuid(orderCreateRequestVo.getProductUuid())
			.productQuantity(orderCreateRequestVo.getProductQuantity())
			.totalAmount(orderCreateRequestVo.getTotalAmount())
			.address(orderCreateRequestVo.getAddress())
			.detailAddress(orderCreateRequestVo.getDetailAddress())
			.phone1(orderCreateRequestVo.getPhone1())
			.userName(orderCreateRequestVo.getUserName())
			.orderStatus(OrderStatus.COMPLETE)
			.build();

		orderService.createOrder(orderCreateRequestDto, authUserDetail.getUserId());

		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			null);
	}

	//주문 목록 조회
	@GetMapping("/list")
	@Operation(summary = "주문 목록 조회", description = "주문 목록 조회")
	public BaseResponse<List<OrderResponseVo>> getAllOrders(
		@AuthenticationPrincipal AuthUserDetail authUserDetail
	) {
		// 서비스에서 목록 가져오기
		//List<Orders> ordersList = orderService.getAllOrders(authUserDetail.getUserId());

		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			orderService.getAllOrders(authUserDetail.getUserId()).stream().map(
				OrderResponseDto::toVo
			).toList());

	}

	@GetMapping("/getOrderOne/{orderId}")
	@Operation(summary = "단일 주문 조회", description = "단일 주문 조회")
	public BaseResponse<OrderResponseVo> getOneOrder(
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable("orderId") String orderId
	) {
		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			orderService.getOneOrder(authUserDetail.getUserId(), orderId).toVo());
	}

	// 주문 상태 변경
	@PutMapping("/cancelOrder/{orderId}")
	@Operation(summary = "주문 취소", description = "주문 취소")
	public BaseResponse<Void> cancelOrderStatus(
		//@RequestBody OrderCancelRequestVo orderCancelRequestVo,
		@AuthenticationPrincipal AuthUserDetail authUserDetail,
		@PathVariable("orderId") String orderId) {

		orderService.cancelOrderStatus(authUserDetail.getUserId(), orderId);
		return new BaseResponse<>(
			HttpStatus.OK,
			SUCCESS.isSuccess(),
			SUCCESS.getMessage(),
			SUCCESS.getCode(),
			null);
	}

}

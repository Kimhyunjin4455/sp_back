package starbucks3355.starbucksServer.domainOrders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderCreateRequestDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.OrderResponseDto;
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

		// OrderCreateRequestDto를 kakaoRequestApproveDto로 변환
		// KakaoRequestApproveDto kakaoRequestApproveDto = KakaoRequestApproveDto.builder()
		// 	.partnerUserId(userId)
		// 	.build();
		// // 결제 승인을 통해 필요한 정보 가져오기
		// KakaoResponseApproveDto kakaoResponseApproveDto = kakaoService.getKakaoPayApprove(kakaoRequestApproveDto);
		//
		// Integer totalAmount = kakaoResponseApproveDto.getAmount().getTotal();
		// String productName = kakaoResponseApproveDto.getProductName();
		// Integer productQuantity = kakaoResponseApproveDto.getProductQuantity();
		// String orderId = kakaoResponseApproveDto.getPartnerOrderId();
		//
		// // OrderCreateRequestDto에 결제 정보 업데이트
		// orderCreateRequestDto.updatePaymentInfo(totalAmount, productName, productQuantity, orderId);

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
	// @Transactional
	// public void updateOrderStatus(OrderUpdateRequestDto orderUpdateRequestDto) {
	// 	log.info("서비스-uuid:" + orderUpdateRequestDto.getUuid());
	// 	Orders order = orderRepository.findByUuid(orderUpdateRequestDto.getUuid())
	// 		.orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다."));
	// 	// 주문 상태 변경
	// 	//builder를 통한 상태 업데이트
	// 	orderRepository.save(Orders.builder()
	// 		.id(order.getId())
	// 		.orderDate(order.getOrderDate())
	// 		.totalAmount(order.getTotalAmount())
	// 		.uuid(order.getUuid())
	// 		.userName(order.getUserName())
	// 		.userPhoneNumber(order.getUserPhoneNumber())
	// 		.userAddress(order.getUserAddress())
	// 		.orderStatus(OrderStatus.CANCEL)
	// 		.build());
	// }
}


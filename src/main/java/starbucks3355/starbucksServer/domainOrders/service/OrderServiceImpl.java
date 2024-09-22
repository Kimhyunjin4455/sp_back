package starbucks3355.starbucksServer.domainOrders.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainOrders.dto.request.KakaoRequestReadyDto;
import starbucks3355.starbucksServer.domainOrders.dto.request.OrderCreateRequestDto;
import starbucks3355.starbucksServer.domainOrders.dto.response.KakaoResponseReadyDto;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;
import starbucks3355.starbucksServer.domainOrders.entity.PayStatus;
import starbucks3355.starbucksServer.domainOrders.repository.OrderRepository;
import starbucks3355.starbucksServer.domainOrders.vo.request.OrderCreateRequestVo;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;
	private final KakaoService kakaoService;

	@Override
	public String prepareOrderWithKakaoPay(List<OrderCreateRequestVo> orderCreateRequestVoList, String uuid) {
		// 주문 생성
		String orderId = createOrder(orderCreateRequestVoList, uuid);

		//카카오페이 결제 준비
		OrderCreateRequestDto orderCreateRequestDto = createKakaoRequestReadyDto(orderCreateRequestVoList, orderId,
			uuid);

		//카카오페이 결제 준비 요청
		return prepareKakaoPay(orderCreateRequestDto);
	}

	// 주문 생성
	@Override
	public String createOrder(List<OrderCreateRequestVo> orderCreateRequestVoList, String uuid) {

		// 주문 총 금액 계산
		Integer totalAmount = orderCreateRequestVoList.stream()
			.mapToInt(orderCreateRequestVo -> orderCreateRequestVo.getTotalAmount()
				* orderCreateRequestVo.getQuantity())
			.sum();

		// 첫 번째 상품을 대표 상품명으로 설정
		String productName = orderCreateRequestVoList.get(0).getProductName();

		// 주문 생성
		Orders orders = Orders.builder()
			.userId(uuid)
			.totalAmount(totalAmount)
			.productName(productName)
			.productQuantity(orderCreateRequestVoList.size()) //
			.payStatus(PayStatus.READY)
			.build();

		orderRepository.save(orders);
		// 주문 번호 반환
		return orders.getOrderId();
	}

	// 주문 생성 -> 카카오 결제 준비 요청
	@Override
	public OrderCreateRequestDto createKakaoRequestReadyDto(List<OrderCreateRequestVo> orderCreateRequestVoList,
		String orderId, String uuid) {
		// 주문 생성과 같은 로직을 쓰는 이유 : 목적이 다름
		// 주문 생성은 디비에 저장을 하기 위함
		// 지금 메서드는 -> 카카오 준비요청에 값을 보내기 위함
		Integer totalAmount = orderCreateRequestVoList.stream()
			.mapToInt(orderCreateRequestVo -> orderCreateRequestVo.getTotalAmount()
				* orderCreateRequestVo.getQuantity())
			.sum();

		String itemName = orderCreateRequestVoList.get(0).getProductName();

		int totalQuantity = orderCreateRequestVoList.stream()
			.mapToInt(OrderCreateRequestVo::getQuantity)
			.sum();

		return OrderCreateRequestDto.builder()
			.cid("TC0ONETIME") // 가맹점 코드 (테스트용)
			.partnerOrderId(orderId)
			.partnerUserId(uuid)
			.quantity(totalQuantity)
			.totalAmount(totalAmount)
			.taxFreeAmount(0)
			.productName(itemName)
			.approvalUrl(
				"http://localhost:8080/swagger-ui/index.html?urls.primaryName=%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4#/%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4/getPgToken")
			.cancelUrl("http://localhost:8080/cancel")
			.failUrl("http://localhost:8080/fail")
			.build();

	}

	// 카카오페이 준비 요청 로직 추가
	@Override
	public String prepareKakaoPay(OrderCreateRequestDto orderCreateRequestDto) {
		// OrderCreateRequestDto -> KakaoRequestReadyDto 변환
		KakaoRequestReadyDto kakaoRequestReadyDto =
			convertTo(orderCreateRequestDto);

		// 카카오페이 결제 준비 요청을 처리하는 로직을 호출
		KakaoResponseReadyDto responseReady = kakaoService.getKakaoPayReady(kakaoRequestReadyDto);

		// 결제 페이지로 리다이렉션할 url 반환
		return responseReady.getNextRedirectPcUrl();
	}

	// OrderCreateRequestDto -> KakaoRequestReadyDto 변환
	private KakaoRequestReadyDto convertTo(OrderCreateRequestDto orderCreateRequestDto) {
		return KakaoRequestReadyDto.builder()
			.cid(orderCreateRequestDto.getCid())
			.partnerOrderId(orderCreateRequestDto.getPartnerOrderId())
			.partnerUserId(orderCreateRequestDto.getPartnerUserId())
			.itemName(orderCreateRequestDto.getProductName())
			.quantity(orderCreateRequestDto.getQuantity())
			.totalAmount(orderCreateRequestDto.getTotalAmount())
			.taxFreeAmount(orderCreateRequestDto.getTaxFreeAmount())
			.approvalUrl(orderCreateRequestDto.getApprovalUrl())
			.cancelUrl(orderCreateRequestDto.getCancelUrl())
			.failUrl(orderCreateRequestDto.getFailUrl())
			.build();
	}

	// 주문 번호 생성
	private String generateOrderId() {
		return "ORD" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}

	// 주문 요약 정보 계산

	// 주문 목록 조회
	// public List<Orders> getAllOrders() {
	// 	return orderRepository.findAll();
	// }

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


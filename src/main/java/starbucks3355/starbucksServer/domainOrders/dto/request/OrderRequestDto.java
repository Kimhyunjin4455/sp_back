package starbucks3355.starbucksServer.domainOrders.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderRequestDto {
	private Integer totalAmount;
	private UUID uuid;
	private String userName;
	private String userPhoneNumber;
	private String userAddress;

	// Dto를 Order 엔티티로 변환 toEntity 메서드
	public Orders toEntity(OrderRequestDto orderRequestDto) {
		return Orders.builder()
			.orderDate(LocalDateTime.now())
			.totalAmount(orderRequestDto.getTotalAmount())
			.uuid(orderRequestDto.getUuid())
			.userAddress(orderRequestDto.userAddress)
			.userName(orderRequestDto.userName)
			.userPhoneNumber(orderRequestDto.userPhoneNumber)
			.orderStatus(OrderStatus.COMPLETE)
			.build();
	}
}

package starbucks3355.starbucksServer.domainOrders.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.entity.Orders;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderRequestDto {
	private LocalDateTime orderDate;
	private Integer totalAmount;
	private UUID uuid;
	private String userName;
	private String userPhoneNumber;
	private String userAddress;

	// Dto를 Order 엔티티로 변환 toEntity 메서드
	public Orders toEntity(OrderRequestDto orderRequestDto) {
		return Orders.builder()
			.orderDate(this.orderDate)
			.totalAmount(this.totalAmount)
			.uuId(this.uuid)
			.userAddress(this.userAddress)
			.userName(this.userName)
			.userPhoneNumber(this.userPhoneNumber)
			.build();
	}
}

package starbucks3355.starbucksServer.domainOrders.dto.response;

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
public class OrderResponseDto {
	private Long id;
	private String address;
	private String detailAddress;
	private String orderId;
	private String userId;
	private String userName; // 배송 보낼때 이름 다르게 할 수 있으니
	private Integer productQuantity;
	private String productUuid;
	private Integer totalAmount;
	private OrderStatus orderStatus;
	private String phone1;

	public Orders toVo() {
		return Orders.builder()
			.id(id)
			.address(address)
			.detailAddress(detailAddress)
			.orderId(orderId)
			.userId(userId)
			.userName(userName)
			.productQuantity(productQuantity)
			.productUuid(productUuid)
			.totalAmount(totalAmount)
			.orderStatus(orderStatus)
			.phone1(phone1)
			.build();
	}
}


package starbucks3355.starbucksServer.domainOrders.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;
import starbucks3355.starbucksServer.domainOrders.vo.response.OrderResponseVo;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderResponseDto {
	private Long id;
	private String address;
	private String detailAddress;
	private String userName; // 배송 보낼때 이름 다르게 할 수 있으니
	private Integer productQuantity;
	private Integer totalAmount;
	private OrderStatus orderStatus;
	private String phone1;

	public OrderResponseVo toVo() {
		return OrderResponseVo.builder()
			.id(id)
			.address(address)
			.detailAddress(detailAddress)
			.userName(userName)
			.productQuantity(productQuantity)
			.totalAmount(totalAmount)
			.orderStatus(orderStatus)
			.phone1(phone1)
			.build();
	}
}


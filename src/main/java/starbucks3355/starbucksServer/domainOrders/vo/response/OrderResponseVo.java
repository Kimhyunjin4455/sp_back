package starbucks3355.starbucksServer.domainOrders.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderResponseVo {
	private Long id;
	private String address;
	private String detailAddress;
	private String userName; // 배송 보낼때 이름 다르게 할 수 있으니
	private Integer productQuantity;
	private Integer totalAmount;
	private OrderStatus orderStatus;
	private String phone1;
}


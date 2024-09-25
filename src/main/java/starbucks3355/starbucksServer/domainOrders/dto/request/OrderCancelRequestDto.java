package starbucks3355.starbucksServer.domainOrders.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;

@Getter
@NoArgsConstructor

public class OrderCancelRequestDto {

	private String orderId;
	private String userId;
	private Integer totalAmount;
	private String productName;
	private Integer productQuantity;
	private String productUuid;
	private String address;
	private String detailAddress;
	private String phone1;
	private OrderStatus orderStatus;
	private String userName;

	@Builder
	public OrderCancelRequestDto(String orderId, String userId, Integer totalAmount, String productName,
		Integer productQuantity, String productUuid, String address, String detailAddress, String phone1,
		OrderStatus orderStatus, String userName) {
		this.orderId = orderId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productUuid = productUuid;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phone1 = phone1;
		this.orderStatus = orderStatus;
		this.userName = userName;
	}

}

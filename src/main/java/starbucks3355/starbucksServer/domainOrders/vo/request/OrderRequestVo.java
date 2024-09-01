package starbucks3355.starbucksServer.domainOrders.vo.request;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainOrders.entity.OrderStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestVo {

	private Integer totalAmount;
	private String userName;
	private String userPhoneNumber;
	private String userAddress;
	private UUID uuid;
}

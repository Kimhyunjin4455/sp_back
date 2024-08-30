package starbucks3355.starbucksServer.domainOrders.vo.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderResponseVo {
	private LocalDateTime orderDate;
	private Integer totalAmount;
	private UUID uuId;
	private String userName;
	private String userPhoneNumber;
	private String userAddress;
}


package starbucks3355.starbucksServer.domainOrders.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateRequestVo {
	// 회원한테 받아 올 데이터(프론트)

	private String productName; // 상품명
	private Integer quantity; // 수량
	private Integer totalAmount; // 총액
}

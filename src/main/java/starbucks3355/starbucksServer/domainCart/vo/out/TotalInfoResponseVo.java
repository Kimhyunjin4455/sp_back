package starbucks3355.starbucksServer.domainCart.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TotalInfoResponseVo {
	private Integer totalDiscount;
	private Integer totalPrice;
}

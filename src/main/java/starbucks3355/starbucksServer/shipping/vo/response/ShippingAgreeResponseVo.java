package starbucks3355.starbucksServer.shipping.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingAgreeResponseVo {
	private boolean shippingAgree;

	@Builder
	public ShippingAgreeResponseVo(boolean shippingAgree) {
		this.shippingAgree = shippingAgree;
	}
}

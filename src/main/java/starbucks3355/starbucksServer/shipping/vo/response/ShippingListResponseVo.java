package starbucks3355.starbucksServer.shipping.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingListResponseVo {

	private Long deliveryId;
	private String address;
	private String detailAddress;
	private String phone1;
	private String receiver;
}

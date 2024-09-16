package starbucks3355.starbucksServer.shipping.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingPutRequestVo {
	private Long deliveryId;
	private String address;
	private String addressDetail;
	private String phone1;
	private String phone2;
	private String nickName;
	private String postNumber;
	private String receiver;
}

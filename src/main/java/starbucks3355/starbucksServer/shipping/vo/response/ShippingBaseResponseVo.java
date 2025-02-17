package starbucks3355.starbucksServer.shipping.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ShippingBaseResponseVo {
	private Long deliveryId;
	private String address;
	private String detailAddress;
	private String phone1;
	private String phone2;
	private String message;
	private boolean baseAddress;
	private String postNumber;
	private String receiver;
	private String nickname;

	@Builder
	public ShippingBaseResponseVo(Long deliveryId, String address, String detailAddress, String phone1, String phone2,
		String message, boolean baseAddress, String postNumber, String receiver, String nickname) {
		this.deliveryId = deliveryId;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.message = message;
		this.baseAddress = baseAddress;
		this.postNumber = postNumber;
		this.receiver = receiver;
		this.nickname = nickname;
	}

}

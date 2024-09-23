package starbucks3355.starbucksServer.shipping.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingOneResponseVo {

	// 프론트한테 보낼 값
	private Long deliveryId;
	private String address;
	private String detailAddress;
	private String nickname;
	private String phone1;
	private String phone2;
	private String postNumber;
	private String message;
	private boolean baseAddress;
	private String receiver;

	@Builder
	public ShippingOneResponseVo(String address, String detailAddress, String nickname, String phone1, String phone2,
		String postNumber, String message, boolean baseAddress, String receiver,
		Long deliveryId) {
		this.address = address;
		this.detailAddress = detailAddress;
		this.nickname = nickname;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.postNumber = postNumber;
		this.message = message;
		this.baseAddress = baseAddress;
		this.receiver = receiver;
		this.deliveryId = deliveryId;
	}
}

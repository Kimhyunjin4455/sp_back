package starbucks3355.starbucksServer.shipping.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingModifyRequestVo {
	private String nickname;
	private String address;
	private String detailAddress;
	private String phone1;
	private String phone2;
	private String message;
	private boolean baseAddress;
	private String postNumber;
	private String receiver;

	public ShippingModifyRequestVo(String nickname, String address, String detailAddress, String phone1, String phone2,
		String message, boolean baseAddress, String postNumber, String receiver) {
		this.nickname = nickname;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.message = message;
		this.baseAddress = baseAddress;
		this.postNumber = postNumber;
		this.receiver = receiver;
	}
}

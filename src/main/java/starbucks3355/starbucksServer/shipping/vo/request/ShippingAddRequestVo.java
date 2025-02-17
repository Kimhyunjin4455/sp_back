package starbucks3355.starbucksServer.shipping.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ShippingAddRequestVo {
	private String nickname;
	private String postNumber;
	private String address;
	private String detailAddress;
	private String phone1;
	private String phone2;
	private String message;
	private String receiver;
	private boolean baseAddress;

	@Builder
	public ShippingAddRequestVo(String nickname, String postNumber, String address, String detailAddress,
		String phone1, String phone2, String message, boolean baseAddress, String receiver) {
		this.nickname = nickname;
		this.postNumber = postNumber;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.message = message;
		this.baseAddress = baseAddress;
		this.receiver = receiver;
	}
}

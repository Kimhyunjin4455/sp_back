package starbucks3355.starbucksServer.shipping.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.shipping.entity.ShippingAddress;

@Getter
@NoArgsConstructor
@ToString
public class ShippingAddRequestDto {
	private String nickname;
	private String postNumber;
	private String address;
	private String detailAddress;
	private String phone1;
	private String phone2;
	private String message;
	private boolean baseAddress;
	private String uuid;

	@Builder
	public ShippingAddRequestDto(String nickname, String postNumber, String address, String detailAddress,
		String phone1, String phone2, String message, boolean baseAddress, String uuid) {
		this.nickname = nickname;
		this.postNumber = postNumber;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.message = message;
		this.baseAddress = baseAddress;
		this.uuid = uuid;
	}

	public ShippingAddress toEntity(String memberUuid, ShippingAddRequestDto shippingAddRequestDto) {
		return ShippingAddress.builder()
			.nickname(nickname)
			.postNumber(postNumber)
			.address(address)
			.detailAddress(detailAddress)
			.phone1(phone1)
			.phone2(phone2)
			.message(message)
			.baseAddress(baseAddress)
			.uuid(memberUuid)
			.build();
	}
}

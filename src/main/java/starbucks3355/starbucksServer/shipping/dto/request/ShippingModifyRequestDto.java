package starbucks3355.starbucksServer.shipping.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingModifyRequestDto {
	private String nickname;
	private String address;
	private String detailAddress;
	private String phone1;
	private String phone2;
	private String message;
	private boolean baseAddress;
	private String postNumber;
	private String receiver;

	@Builder
	public ShippingModifyRequestDto(String nickname, String address, String detailAddress, String phone1, String phone2,
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

	// public ShippingAddress toEntity(String uuid, ShippingModifyRequestDto shippingModifyRequestDto) {
	// 	return ShippingAddress.builder()
	// 		.nickname(shippingModifyRequestDto.getNickname())
	// 		.address(shippingModifyRequestDto.getAddress())
	// 		.detailAddress(shippingModifyRequestDto.getDetailAddress())
	// 		.phone1(shippingModifyRequestDto.getPhone1())
	// 		.phone2(shippingModifyRequestDto.getPhone2())
	// 		.message(shippingModifyRequestDto.getMessage())
	// 		.baseAddress(shippingModifyRequestDto.isBaseAddress())
	// 		.postNumber(shippingModifyRequestDto.getPostNumber())
	// 		.receiver(shippingModifyRequestDto.getReceiver())
	// 		.uuid(uuid)
	// 		.build();
	// }
}

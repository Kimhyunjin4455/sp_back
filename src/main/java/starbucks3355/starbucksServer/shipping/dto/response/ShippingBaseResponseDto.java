package starbucks3355.starbucksServer.shipping.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.shipping.vo.response.ShippingBaseResponseVo;

@Getter
@ToString
@NoArgsConstructor
public class ShippingBaseResponseDto {
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
	public ShippingBaseResponseDto(Long deliveryId, String address, String detailAddress,
		String phone1, String phone2, String message, boolean baseAddress, String postNumber, String receiver
		, String nickname) {
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

	public ShippingBaseResponseVo toVo() {
		return ShippingBaseResponseVo.builder()
			.deliveryId(deliveryId)
			.address(address)
			.detailAddress(detailAddress)
			.phone1(phone1)
			.phone2(phone2)
			.message(message)
			.baseAddress(baseAddress)
			.postNumber(postNumber)
			.receiver(receiver)
			.nickname(nickname)
			.build();
	}
}

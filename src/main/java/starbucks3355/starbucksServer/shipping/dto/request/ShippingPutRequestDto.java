package starbucks3355.starbucksServer.shipping.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.shipping.entity.ShippingMemberAddress;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingPutRequestDto {
	private Long deliveryId;
	private String address;
	private String detailAddress;
	private String phone1;
	private String phone2;
	private String nickName;
	private String postNumber;
	private String receiver;

	public ShippingMemberAddress toEntity(ShippingPutRequestDto shippingPutRequestDto) {
		return ShippingMemberAddress.builder()
			.deliveryId(shippingPutRequestDto.getDeliveryId())
			.address(shippingPutRequestDto.getAddress())
			.detailAddress(shippingPutRequestDto.getDetailAddress())
			.nickname(shippingPutRequestDto.getNickName())
			.phone1(shippingPutRequestDto.getPhone1())
			.phone2(shippingPutRequestDto.getPhone2())
			.receiver(shippingPutRequestDto.getReceiver())
			.postNumber(shippingPutRequestDto.getPostNumber())
			.build();
	}
}

package starbucks3355.starbucksServer.shipping.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.shipping.vo.response.ShippingListResponseVo;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingListResponseDto {
	private Long deliveryId;
	private String address;
	private String detailAddress;
	private String phone1;
	private String receiver;

	public ShippingListResponseVo toVo() { // this로 해당 객체의 필드 참조(shippingListResponseDto)
		return ShippingListResponseVo.builder()
			.deliveryId(this.deliveryId)
			.address(this.address)
			.detailAddress(this.detailAddress)
			.phone1(this.phone1)
			.receiver(this.receiver)
			.build();
	}
}

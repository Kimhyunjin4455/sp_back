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

	@Builder
	public ShippingBaseResponseDto(Long deliveryId, String address, String detailAddress) {
		this.deliveryId = deliveryId;
		this.address = address;
		this.detailAddress = detailAddress;
	}

	public ShippingBaseResponseVo toVo() {
		return ShippingBaseResponseVo.builder()
			.deliveryId(deliveryId)
			.address(address)
			.detailAddress(detailAddress)
			.build();
	}
}

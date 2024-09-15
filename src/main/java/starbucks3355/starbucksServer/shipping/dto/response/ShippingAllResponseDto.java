package starbucks3355.starbucksServer.shipping.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.shipping.vo.response.ShippingAllResponseVo;

@Getter
@NoArgsConstructor
@ToString
public class ShippingAllResponseDto {
	private Long deliveryId;
	private String nickname;
	private String address;
	private String detailAddress;

	@Builder
	public ShippingAllResponseDto(Long deliveryId, String nickname, String address, String detailAddress) {
		this.deliveryId = deliveryId;
		this.nickname = nickname;
		this.address = address;
		this.detailAddress = detailAddress;
	}

	public ShippingAllResponseVo toVo() {
		return ShippingAllResponseVo.builder()
			.deliveryId(deliveryId)
			.nickname(nickname)
			.address(address)
			.detailAddress(detailAddress)
			.build();
	}
}

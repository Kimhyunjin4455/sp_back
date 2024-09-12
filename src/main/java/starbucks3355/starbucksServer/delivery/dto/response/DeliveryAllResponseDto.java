package starbucks3355.starbucksServer.delivery.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.delivery.vo.response.DeliveryAllResponseVo;

@Getter
@NoArgsConstructor
@ToString
public class DeliveryAllResponseDto {
	private Long deliveryId;
	private String nickname;
	private String address;
	private String detailAddress;

	@Builder
	public DeliveryAllResponseDto(Long deliveryId, String nickname, String address, String detailAddress) {
		this.deliveryId = deliveryId;
		this.nickname = nickname;
		this.address = address;
		this.detailAddress = detailAddress;
	}

	public DeliveryAllResponseVo toVo() {
		return DeliveryAllResponseVo.builder()
			.deliveryId(deliveryId)
			.nickname(nickname)
			.address(address)
			.detailAddress(detailAddress)
			.build();
	}
}

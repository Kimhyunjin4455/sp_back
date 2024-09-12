package starbucks3355.starbucksServer.delivery.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.delivery.vo.response.DeliveryBaseResponseVo;

@Getter
@ToString
@NoArgsConstructor
public class DeliveryBaseResponseDto {
	private Long deliveryId;
	private String address;
	private String detailAddress;

	@Builder
	public DeliveryBaseResponseDto(Long deliveryId, String address, String detailAddress) {
		this.deliveryId = deliveryId;
		this.address = address;
		this.detailAddress = detailAddress;
	}

	public DeliveryBaseResponseVo toVo() {
		return DeliveryBaseResponseVo.builder()
			.deliveryId(deliveryId)
			.address(address)
			.detailAddress(detailAddress)
			.build();
	}
}

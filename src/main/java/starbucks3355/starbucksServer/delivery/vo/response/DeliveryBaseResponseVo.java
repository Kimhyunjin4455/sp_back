package starbucks3355.starbucksServer.delivery.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class DeliveryBaseResponseVo {
	private Long deliveryId;
	private String address;
	private String detailAddress;

	@Builder
	public DeliveryBaseResponseVo(Long deliveryId, String address, String detailAddress) {
		this.deliveryId = deliveryId;
		this.address = address;
		this.detailAddress = detailAddress;
	}

}

package starbucks3355.starbucksServer.delivery.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class DeliveryAllResponseVo {
	private Long deliveryId;
	private String nickname;
	private String address;
	private String detailAddress;

	@Builder
	public DeliveryAllResponseVo(Long deliveryId, String nickname, String address, String detailAddress) {
		this.deliveryId = deliveryId;
		this.nickname = nickname;
		this.address = address;
		this.detailAddress = detailAddress;
	}
}

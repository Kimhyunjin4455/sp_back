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
	private String nickname;
	private String receiver;
	private String postNumber;
	private String phone1;
	private String phone2;
	private String message;
	private boolean baseAddress;

	public ShippingListResponseVo toVo() { // this로 해당 객체의 필드 참조(shippingListResponseDto)
		return ShippingListResponseVo.builder()
			.deliveryId(this.deliveryId) //ㅇ
			.address(this.address) //ㅇ
			.detailAddress(this.detailAddress) //ㅇ
			.nickname(this.nickname)
			.receiver(this.receiver) // ㅇ
			.postNumber(this.postNumber)
			.phone1(this.phone1)//ㅇ
			.phone2(this.phone2)
			.message(this.message)
			.baseAddress(this.baseAddress)
			.build();
	}
}

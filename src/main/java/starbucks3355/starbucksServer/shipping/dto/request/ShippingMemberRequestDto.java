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
public class ShippingMemberRequestDto {
	private String address;        // ex: "부산진구 엄광로 210"
	private String detailAddress;  // ex: "1층 101호"
	private String phone1;         // 연락처1
	private String phone2;         // 연락처2
	private String nickName;       // 별칭
	private String postNumber;     // 우편번호
	private String receiver;       // 수령자
	private boolean baseAddress;   // 기본 배송지 여부
	private String message; // 메시지 여부
	private String uuid; //회원의 uuid

	// 이 부분 다시 공부하기 왜 uuid인지 이해가 안됨
	public ShippingMemberAddress toEntity(String uuid) {
		return ShippingMemberAddress.builder()
			.address(this.getAddress())
			.detailAddress(this.getDetailAddress())
			.nickname(this.getNickName())
			.phone1(this.getPhone1())
			.phone2(this.getPhone2())
			.receiver(this.getReceiver())
			.postNumber(this.getPostNumber())
			.baseAddress(this.isBaseAddress())
			.message(this.getMessage())
			.uuid(uuid)
			.build();
	}
}

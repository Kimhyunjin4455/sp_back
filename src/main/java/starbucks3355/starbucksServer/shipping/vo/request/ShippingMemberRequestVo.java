package starbucks3355.starbucksServer.shipping.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingMemberRequestVo {
	private String address;        // ex: "부산진구 엄광로 210"
	private String detailAddress;  // ex: "1층 101호"
	private String phone1;         // 연락처1
	private String phone2;         // 연락처2
	private String nickName;       // 별칭
	private String postNumber;     // 우편번호
	private String receiver;       // 수령자
	private String message; // 메시지 여부
	private boolean baseAddress;   // 기본 배송지 여부

}

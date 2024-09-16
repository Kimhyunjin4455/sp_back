package starbucks3355.starbucksServer.shipping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class ShippingMemberAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 30)
	private Long deliveryId;
	@Column(nullable = false, length = 200)
	private String address; // 부산진구
	@Column(nullable = false)
	private boolean baseAddress; // 기본 주소 선택유무
	@Column(length = 20)
	private String nickname; // 별칭
	@Column(nullable = false, length = 30, unique = true)
	private String receiver; // 수령자
	@Column(nullable = false, length = 30)
	private String phone1; //제 1연락처
	@Column(length = 30)
	private String phone2; //제 2연락처
	@Column(length = 30)
	private String message; // 배송 메세지
	@Column(nullable = false)
	private String uuid; //회원의 uuid
	@Column(nullable = false, length = 30)
	private String postNumber; // 우편번호
	@Column(nullable = false, length = 200)
	private String detailAddress; // 주소상세

	@Builder
	public ShippingMemberAddress(Long deliveryId, String address, String detailAddress, boolean baseAddress,
		String nickname, String receiver, String phone1, String phone2, String postNumber, String uuid
		, String message) {
		this.deliveryId = deliveryId;
		this.address = address;
		this.detailAddress = detailAddress;
		this.baseAddress = baseAddress;
		this.nickname = nickname;
		this.message = message;
		this.uuid = uuid;
		this.postNumber = postNumber;
		this.receiver = receiver;
		this.phone1 = phone1;
		this.phone2 = phone2;
	}
}

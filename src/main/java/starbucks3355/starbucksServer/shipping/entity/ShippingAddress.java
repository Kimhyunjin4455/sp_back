package starbucks3355.starbucksServer.shipping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class ShippingAddress {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long deliveryId;
	// @(nullable = false, length = 30)
	// private Long memberAddressId; // 회원 주소 id
	@Column(length = 15)
	private String nickname; // 배송지 별칭
	@Column(nullable = false, length = 20)
	private String postNumber; // 우편번호
	@Column(nullable = false, length = 100)
	private String address; // ex) 부산진구 엄광로 210
	@Column(nullable = false, length = 100)
	private String detailAddress; // ex) 1층 101호
	@Column(nullable = false, length = 20)
	private String phone1; // 전화번호
	@Column(length = 20)
	private String phone2; // 전화번호2
	@Column(length = 20)
	private String message;
	@Column(nullable = false)
	private boolean baseAddress = true; // 기본 배송지 유무
	@Column(nullable = false)
	private String uuid; // 회원의 uuid 값 받아오기
	@Column(nullable = false, length = 30)
	private String receiver;

	@Builder
	public ShippingAddress(Long deliveryId, String nickname, String postNumber, String address, String detailAddress,
		String phone1, String phone2, String message, boolean baseAddress, String uuid, String receiver) {
		this.deliveryId = deliveryId;
		this.nickname = nickname;
		this.postNumber = postNumber;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.message = message;
		this.baseAddress = baseAddress;
		this.uuid = uuid;
		this.receiver = receiver;
	}

	// 더티 체킹
	public void changeBaseAddress(boolean baseAddress) {
		this.baseAddress = baseAddress;
	}
}


package starbucks3355.starbucksServer.delivery.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.delivery.entity.Delivery;

@Getter
@NoArgsConstructor
@ToString
public class DeliveryAddRequestDto {
	private String nickname;
	private String postNumber;
	private String address;
	private String detailAddress;
	private String phone1;
	private String phone2;
	private String message;
	private boolean isBase;

	@Builder
	public DeliveryAddRequestDto(String nickname, String postNumber, String address, String detailAddress,
		String phone1, String phone2, String message, boolean isBase) {
		this.nickname = nickname;
		this.postNumber = postNumber;
		this.address = address;
		this.detailAddress = detailAddress;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.message = message;
		this.isBase = isBase;
	}

	public Delivery toEntity() {
		return Delivery.builder()
			.nickname(nickname)
			.postNumber(postNumber)
			.address(address)
			.detailAddress(detailAddress)
			.phone1(phone1)
			.phone2(phone2)
			.message(message)
			.isBase(isBase)
			.build();
	}
}

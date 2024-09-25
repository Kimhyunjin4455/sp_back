package starbucks3355.starbucksServer.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.auth.vo.request.FindUserIdRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class FindUserIdRequestDto {
	private String email;

	@Builder
	public FindUserIdRequestDto(String email) {
		this.email = email;
	}

	public static FindUserIdRequestDto from(FindUserIdRequestVo findUserIdRequestVo) {
		return FindUserIdRequestDto.builder()
			.email(findUserIdRequestVo.getEmail())
			.build();
	}
}

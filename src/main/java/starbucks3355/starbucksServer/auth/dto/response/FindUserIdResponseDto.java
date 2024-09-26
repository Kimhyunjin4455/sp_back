package starbucks3355.starbucksServer.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.auth.vo.response.FindUserIdResponseVo;

@Getter
@NoArgsConstructor
public class FindUserIdResponseDto {
	private String userId;
	private String message;

	@Builder
	public  FindUserIdResponseDto(String userId, String message) {

		this.userId = userId;
		this.message = message;
	}

	public FindUserIdResponseVo toVo() {

		return FindUserIdResponseVo.builder()
			.userId(userId)
			.message(message)
			.build();
	}
}

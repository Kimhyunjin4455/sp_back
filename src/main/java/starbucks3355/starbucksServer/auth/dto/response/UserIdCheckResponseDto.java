package starbucks3355.starbucksServer.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.auth.vo.response.UserIdCheckResponseVo;

@Getter
@NoArgsConstructor
public class UserIdCheckResponseDto {
	private boolean isDuplicated;
	private String message;

	@Builder
	public UserIdCheckResponseDto(boolean isDuplicated, String message) {
		this.isDuplicated = isDuplicated;
		this.message = message;
	}

	public UserIdCheckResponseVo toVo() {
		return UserIdCheckResponseVo.builder()
			.isDuplicated(isDuplicated)
			.message(message)
			.build();
	}
}

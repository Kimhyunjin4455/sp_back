package starbucks3355.starbucksServer.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserIdCheckResponseVo {
	private boolean isDuplicated;
	private String message;

	@Builder
	public UserIdCheckResponseVo(boolean isDuplicated, String message) {
		this.isDuplicated = isDuplicated;
		this.message = message;
	}
}

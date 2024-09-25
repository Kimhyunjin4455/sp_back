package starbucks3355.starbucksServer.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindUserIdResponseVo {
	private String userId;
	private String message;

	@Builder
	public FindUserIdResponseVo(String userId, String message) {
		this.userId = userId;
		this.message = message;
	}

}

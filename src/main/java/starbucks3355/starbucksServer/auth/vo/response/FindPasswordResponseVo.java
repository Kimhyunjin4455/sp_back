package starbucks3355.starbucksServer.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindPasswordResponseVo {
	private boolean isUser;

	@Builder
	public FindPasswordResponseVo(boolean isUser) {
		this.isUser = isUser;
	}
}

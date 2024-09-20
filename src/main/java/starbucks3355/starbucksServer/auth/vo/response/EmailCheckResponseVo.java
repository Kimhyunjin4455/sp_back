package starbucks3355.starbucksServer.auth.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailCheckResponseVo {
	private boolean isDuplicated;
	private String message;

	@Builder
	public EmailCheckResponseVo(boolean isDuplicated, String message) {
		this.isDuplicated = isDuplicated;
		this.message = message;
	}

}

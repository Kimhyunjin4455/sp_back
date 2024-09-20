package starbucks3355.starbucksServer.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.auth.vo.response.EmailCheckResponseVo;

@Getter
@NoArgsConstructor
public class EmailCheckResponseDto {
	private boolean isDuplicated;
	private String message;

	@Builder
	public EmailCheckResponseDto(boolean isDuplicated, String message) {
		this.isDuplicated = isDuplicated;
		this.message = message;
	}

	public EmailCheckResponseVo toVo() {
		return EmailCheckResponseVo.builder()
			.isDuplicated(isDuplicated)
			.message(message)
			.build();
	}
}

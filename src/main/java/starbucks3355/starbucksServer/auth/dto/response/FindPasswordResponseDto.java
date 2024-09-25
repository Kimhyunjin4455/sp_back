package starbucks3355.starbucksServer.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.auth.vo.response.FindPasswordResponseVo;

@Getter
@NoArgsConstructor
public class FindPasswordResponseDto {
	private boolean isUser;

	@Builder
	public FindPasswordResponseDto(boolean isUser) {
		this.isUser = isUser;
	}

	public FindPasswordResponseVo toVo() {
		return FindPasswordResponseVo.builder()
			.isUser(isUser)
			.build();
	}

}

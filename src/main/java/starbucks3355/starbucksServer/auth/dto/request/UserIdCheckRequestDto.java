package starbucks3355.starbucksServer.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.auth.vo.request.UserIdCheckRequestVo;

@Getter
@NoArgsConstructor
@ToString

public class UserIdCheckRequestDto {
	private String userId;

	@Builder
	public UserIdCheckRequestDto(String userId) {
		this.userId = userId;
	}

	public static EmailCheckRequestDto from(UserIdCheckRequestVo userIdCheckRequestVo) {
		return EmailCheckRequestDto.builder()
			.email(userIdCheckRequestVo.getUserId())
			.build();
	}
}

package starbucks3355.starbucksServer.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.auth.vo.request.FindPasswordRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class FindPasswordRequestDto {
	private String userId;
	private String email;

	@Builder
	public FindPasswordRequestDto(String userId, String email) {
		this.userId = userId;
		this.email = email;
	}

	public static FindPasswordRequestDto from(FindPasswordRequestVo findPasswordRequestVo) {
		return FindPasswordRequestDto.builder()
			.userId(findPasswordRequestVo.getUserId())
			.email(findPasswordRequestVo.getEmail())
			.build();
	}

}

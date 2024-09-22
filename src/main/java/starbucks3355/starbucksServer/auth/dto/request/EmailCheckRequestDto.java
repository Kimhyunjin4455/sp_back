package starbucks3355.starbucksServer.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.auth.vo.request.EmailCheckRequestVo;

@Getter
@NoArgsConstructor
@ToString

public class EmailCheckRequestDto {
	private String email;

	@Builder
	public EmailCheckRequestDto(String email) {
		this.email = email;
	}

	public static EmailCheckRequestDto from(EmailCheckRequestVo emailCheckRequestVo) {
		return EmailCheckRequestDto.builder()
			.email(emailCheckRequestVo.getEmail())
			.build();
	}
}

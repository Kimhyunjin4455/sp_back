package starbucks3355.starbucksServer.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import starbucks3355.starbucksServer.auth.vo.request.UpdatePasswordRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class UpdatePasswordRequestDto {
	// private String accessToken;
	private String newPassword;

	@Builder
	public UpdatePasswordRequestDto(String newPassword) {
		// this.accessToken = accessToken;
		this.newPassword = newPassword;
	}

	public static UpdatePasswordRequestDto from(UpdatePasswordRequestVo updatePasswordRequestVo) {
		return UpdatePasswordRequestDto.builder()
			.newPassword(updatePasswordRequestVo.getNewPassword())
			.build();
	}



}

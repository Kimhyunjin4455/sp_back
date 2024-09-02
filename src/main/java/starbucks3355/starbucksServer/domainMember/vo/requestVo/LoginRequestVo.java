package starbucks3355.starbucksServer.domainMember.vo.requestVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestVo {
	private String userId;
	private String password;
}

package starbucks3355.starbucksServer.auth.vo.in;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SignUpRequestVo {
	private String userId;
	private String email;
	private String password;
	private String name;
	private String nickname;

}

package starbucks3355.starbucksServer.auth.vo.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindPasswordRequestVo {
	private String userId;
	private String email;
}

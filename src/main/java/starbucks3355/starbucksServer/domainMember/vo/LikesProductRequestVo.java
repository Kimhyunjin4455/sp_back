package starbucks3355.starbucksServer.domainMember.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class LikesProductRequestVo {
	private String uuid;
	private String productUuid;
	private boolean isLikes;
}

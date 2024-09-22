package starbucks3355.starbucksServer.domainReview.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewModifyRequestVo {
	private String content;
	private Integer reviewScore;

}

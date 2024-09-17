package starbucks3355.starbucksServer.domainReview.vo.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewModifyRequestVo {
	private String content;
	private Integer reviewScore;
	private LocalDateTime regDate, modDate;
}

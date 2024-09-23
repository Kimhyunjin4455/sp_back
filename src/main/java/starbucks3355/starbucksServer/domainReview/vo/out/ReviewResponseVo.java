package starbucks3355.starbucksServer.domainReview.vo.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponseVo {
	private String content;
	private String authorName;
	private Integer reviewScore;
	private LocalDateTime regDate, modDate;
}

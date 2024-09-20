package starbucks3355.starbucksServer.domainReview.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewScoreResponseVo {
	String reviewscoreAvg;
	String reviewcount;

}

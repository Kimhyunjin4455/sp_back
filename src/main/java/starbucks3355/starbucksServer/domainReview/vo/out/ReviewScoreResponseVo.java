package starbucks3355.starbucksServer.domainReview.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewScoreResponseVo {
	Double reviewscoreAvg;
	Long reviewcount;

}

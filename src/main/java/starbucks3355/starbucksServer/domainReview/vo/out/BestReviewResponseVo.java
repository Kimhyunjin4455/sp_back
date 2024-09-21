package starbucks3355.starbucksServer.domainReview.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestReviewResponseVo {
	private String reviewUuid;
	private String productUuid;
}

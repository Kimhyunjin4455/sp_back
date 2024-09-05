package starbucks3355.starbucksServer.domainReview.vo.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductReviewResponseVo {
	private String content;
	private String reviewUuid;
	private Integer reviewScore;
	private String productUuid;
	private LocalDateTime regDate, modDate;
}

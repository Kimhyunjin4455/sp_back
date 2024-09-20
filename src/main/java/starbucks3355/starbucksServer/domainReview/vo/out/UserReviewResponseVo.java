package starbucks3355.starbucksServer.domainReview.vo.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserReviewResponseVo {
	private String content;
	private String reviewUuid;
	private Integer reviewScore;
	private String productUuid; // 회원이 구매한 '상품'에 대한 리뷰
	private String authorName;
	private LocalDateTime regDate, modDate;
}

package starbucks3355.starbucksServer.domainReview.vo.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewRequestVo {
	private String content;
	private String reviewUuid;
	private Integer reivewScore;
	private String productUuid; // 회원이 구매한 '상품'에 대한 리뷰
	private String memberUuid; // 미확실 필드: 시큐리티의 토큰처리에 따라 사용여부 결정
	private LocalDateTime regDate, modDate;
}

package starbucks3355.starbucksServer.domainReview.dto.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainReview.entity.Review;

@Getter
@Builder
public class ReviewRequestDto {
	private String content;
	private String reviewUuid;
	private Integer reviewScore;
	private String productUuid; // 회원이 구매한 '상품'에 대한 리뷰
	private String memberUuid; // 액세스 토큰을 통해 값 입력받음
	private LocalDateTime regDate, modDate;

	public Review toEntity(String reviewUuid, String productUuid, String memberUuid) {
		return Review.builder()
			.content(content)
			.reviewUuid(reviewUuid)
			.reviewScore(reviewScore)
			.productUuid(productUuid)
			.memberUuid(memberUuid)
			.reviewViewCount(0)
			.build();
	}
}

package starbucks3355.starbucksServer.domainReview.dto.in;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainReview.entity.Review;
import starbucks3355.starbucksServer.domainReview.vo.in.ReviewRequestVo;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {
	private String content;
	private Integer reviewScore;
	private String productUuid; // 회원이 구매한 '상품'에 대한 리뷰
	private String authorName; // 액세스 토큰에서 받은 uuid값을 가공해서 삽입 <- 닉네임으로 할 경우 변경시 문제 발생

	@Builder
	public ReviewRequestDto(String content, Integer reviewScore, String productUuid, String authorName) {
		this.content = content;
		this.reviewScore = reviewScore;
		this.productUuid = productUuid;
		this.authorName = authorName;
	}

	public Review toEntity() {
		return Review.builder()
			.content(content)
			.reviewUuid(UUID.randomUUID().toString())
			.reviewScore(reviewScore)
			.productUuid(productUuid)
			.authorName(authorName)
			.reviewViewCount(0)
			.build();
	}

	public static ReviewRequestDto of(ReviewRequestVo reviewRequestVo) {
		return ReviewRequestDto.builder()
			.content(reviewRequestVo.getContent())
			.reviewScore(reviewRequestVo.getReviewScore())
			.productUuid(reviewRequestVo.getProductUuid())
			.authorName(reviewRequestVo.getAuthorName())
			.build();
	}
}

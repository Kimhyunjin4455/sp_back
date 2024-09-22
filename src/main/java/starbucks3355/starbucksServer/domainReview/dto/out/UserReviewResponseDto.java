package starbucks3355.starbucksServer.domainReview.dto.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainReview.vo.out.UserReviewResponseVo;

@Getter
@Builder
public class UserReviewResponseDto {
	private String content;
	private String reviewUuid;
	private Integer reviewScore;
	private String productUuid; // 회원이 구매한 '상품'에 대한 리뷰
	private String authorName;
	private LocalDateTime regDate, modDate;

	public UserReviewResponseDto(
		String content,
		String reviewUuid,
		Integer reviewScore,
		String productUuid,
		String authorName,
		LocalDateTime regDate,
		LocalDateTime modDate) {
		this.content = content;
		this.reviewUuid = reviewUuid;
		this.reviewScore = reviewScore;
		this.productUuid = productUuid;
		this.authorName = authorName;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public UserReviewResponseVo dtoToResponseVo() {
		return UserReviewResponseVo.builder()
			.content(content)
			.reviewUuid(reviewUuid)
			.reviewScore(reviewScore)
			.productUuid(productUuid)
			.authorName(authorName)
			.regDate(getRegDate())
			.modDate(getModDate())
			.build();
	}
}

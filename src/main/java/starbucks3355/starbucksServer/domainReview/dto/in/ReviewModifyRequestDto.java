package starbucks3355.starbucksServer.domainReview.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainReview.vo.in.ReviewModifyRequestVo;

@Getter
@NoArgsConstructor
public class ReviewModifyRequestDto {
	private String content;
	private Integer reviewScore;

	@Builder
	public ReviewModifyRequestDto(String content, Integer reviewScore) {
		this.content = content;
		this.reviewScore = reviewScore;
	}

	public static ReviewModifyRequestDto of(ReviewModifyRequestVo reviewModifyRequestVo) {
		return ReviewModifyRequestDto.builder()
			.content(reviewModifyRequestVo.getContent())
			.reviewScore(reviewModifyRequestVo.getReviewScore())
			.build();
	}
}

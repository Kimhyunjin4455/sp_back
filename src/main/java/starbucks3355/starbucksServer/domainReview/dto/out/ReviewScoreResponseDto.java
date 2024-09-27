package starbucks3355.starbucksServer.domainReview.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainReview.vo.out.ReviewScoreResponseVo;

@Getter
@NoArgsConstructor
public class ReviewScoreResponseDto {
	Double reviewscoreAvg;
	Long reviewcount;

	@Builder
	public ReviewScoreResponseDto(Double reviewscoreAvg, Long reviewcount) {
		this.reviewscoreAvg = reviewscoreAvg;
		this.reviewcount = reviewcount;
	}

	public ReviewScoreResponseVo dtoToResponseVo() {
		return ReviewScoreResponseVo.builder()
			.reviewscoreAvg(reviewscoreAvg)
			.reviewcount(reviewcount)
			.build();
	}

}

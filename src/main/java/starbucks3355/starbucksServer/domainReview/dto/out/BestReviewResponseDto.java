package starbucks3355.starbucksServer.domainReview.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import starbucks3355.starbucksServer.domainReview.vo.out.BestReviewResponseVo;

@Getter
@NoArgsConstructor
public class BestReviewResponseDto {
	private String reviewUuid;
	private String productUuid;

	@Builder
	public BestReviewResponseDto(
		String reviewUuid,
		String productUuid) {
		this.reviewUuid = reviewUuid;
		this.productUuid = productUuid;
	}

	public BestReviewResponseVo dtoToResponseVo() {
		return BestReviewResponseVo.builder()
			.reviewUuid(reviewUuid)
			.productUuid(productUuid)
			.build();
	}
}

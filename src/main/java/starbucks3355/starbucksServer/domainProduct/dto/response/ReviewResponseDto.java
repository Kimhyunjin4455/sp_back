package starbucks3355.starbucksServer.domainProduct.dto.response;

import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainProduct.vo.response.ReviewResponseVo;

@Getter
@Builder
public class ReviewResponseDto {
	private Double reviewScore;
	private Integer reviewCount;

	public ReviewResponseDto(Double reviewScore, Integer reviewCount) {
		this.reviewScore = reviewScore;
		this.reviewCount = reviewCount;
	}

	public ReviewResponseVo dtoToResponseVo() {
		return ReviewResponseVo.builder()
			.reviewScore(reviewScore)
			.reviewCount(reviewCount)
			.build();
	}
}

package starbucks3355.starbucksServer.domainProduct.dto.response;

import starbucks3355.starbucksServer.domainProduct.vo.response.ReviewResponseVo;

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

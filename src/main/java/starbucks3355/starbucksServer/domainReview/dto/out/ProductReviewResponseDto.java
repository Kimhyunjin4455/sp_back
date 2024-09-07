package starbucks3355.starbucksServer.domainReview.dto.out;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import starbucks3355.starbucksServer.domainReview.vo.out.ProductReviewResponseVo;

@Getter
@Builder
@AllArgsConstructor
public class ProductReviewResponseDto {
	private String content;
	private String reviewUuid;
	private Integer reviewScore;
	private String productUuid;
	private String memberUuid;
	private LocalDateTime regDate, modDate;

	public ProductReviewResponseVo dtoToResponseVo() {
		return ProductReviewResponseVo.builder()
			.content(content)
			.reviewScore(reviewScore)
			.reviewUuid(reviewUuid)
			.productUuid(productUuid)
			.regDate(getRegDate())
			.modDate(getModDate())
			.build();
	}

}

package starbucks3355.starbucksServer.domainReview.dto.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewModifyRequestDto {
	private String content;
	private Integer reviewScore;
	private LocalDateTime regDate, modDate;
}

package starbucks3355.starbucksServer.domainReview.vo.in;

import lombok.Getter;

@Getter
public class ReviewRequestVo {
	private String content;
	private Integer reviewScore;
	private String productUuid;
	// private String authorName; // 액세스 토큰에서 받은 uuid값을 가공해서 삽입 <- 닉네임으로 할 경우 변경시 문제 발생
	// 회원이 구매한 '상품'에 대한 리뷰
	// private String memberUuid; // 미확실 필드: 시큐리티의 토큰처리에 따라 사용여부 결정
}

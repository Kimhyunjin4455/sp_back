package starbucks3355.starbucksServer.domainCart.vo.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartResponseVo {
	private String productUuid;
	private String memberUuid;
	private boolean isChecked;
	private Integer limitQuantity;
	private Integer currentQuantity;
	private LocalDateTime regDate, modDate;
}

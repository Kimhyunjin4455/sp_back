package starbucks3355.starbucksServer.domainCart.vo.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartRequestVo {
	private String productUuid;
	//private String memberUuid;
	private boolean isChecked;
	private Integer limitQuantity;
	// private Integer currentQuantity;
	private LocalDateTime regDate, modDate;

}

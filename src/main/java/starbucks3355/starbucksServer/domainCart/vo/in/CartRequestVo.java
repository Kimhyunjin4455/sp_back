package starbucks3355.starbucksServer.domainCart.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestVo {
	private String productUuid;
	// private String memberUuid;
	// private boolean isChecked;
	// private Integer limitQuantity;
	// private Integer currentQuantity;
	// private LocalDateTime regDate, modDate;

	@Builder
	public CartRequestVo(String productUuid) {
		this.productUuid = productUuid;
	}

}

package starbucks3355.starbucksServer.vendor.vo.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductListByCategoryResponseVo {
	private Long lastId;
	private Integer pageSize;
	private Boolean hasNext;
	private List<String> productUuidList;

	@Builder
	public ProductListByCategoryResponseVo(
		Long lastId,
		Integer pageSize,
		Boolean hasNext,
		List<String> productUuidList) {
		this.lastId = lastId;
		this.pageSize = pageSize;
		this.hasNext = hasNext;
		this.productUuidList = productUuidList;
	}
}

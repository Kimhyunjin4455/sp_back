package starbucks3355.starbucksServer.domainProduct.repository;

import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.common.utils.CursorPage;

@Repository
public interface ProductListRepositoryCustom {
	CursorPage<String> getProductList(
		Long lastId,
		Integer pageSize,
		Integer page
	);

	CursorPage<String> getSearchedProductList(
		String keyword,
		Long lastId,
		Integer pageSize,
		Integer page
	);

}

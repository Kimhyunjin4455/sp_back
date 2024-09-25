package starbucks3355.starbucksServer.domainProduct.service;

import org.springframework.data.domain.Slice;

import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsPriceResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductFlagsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductsResponseDto;

public interface ProductService {
	public void addProduct(ProductRequestDto productRequestDto);

	// public List<ProductResponseDto> getProducts();

	public Slice<ProductsResponseDto> getProducts(int page, int size);

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

	ProductFlagsResponseDto getProductFlags(String productUuid);

	public ProductResponseDto getProduct(String productUuid);

	// public List<ProductInfoResponseDto> getProductsInfo(String productSearchInfo);

	public ProductDetailsPriceResponseDto getProductPrice(String productUuid);

	public DiscountResponseDto getDiscountInfo(String productUuid);

	CursorPage<String> getRecentlyViewed(
		String memberUuid,
		Integer pageSize,
		Integer page
	);

	void addRecentlyViewed(String memberUuid, String productUuid);

	public void updateProduct(ProductRequestDto productRequestDto);

	public void deleteProduct(String uuid);

}

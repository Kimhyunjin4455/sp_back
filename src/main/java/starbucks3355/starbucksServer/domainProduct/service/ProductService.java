package starbucks3355.starbucksServer.domainProduct.service;

import java.util.List;

import org.springframework.data.domain.Slice;

import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsPriceResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductFlagsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductInfoResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductsResponseDto;

public interface ProductService {
	public void addProduct(ProductRequestDto productRequestDto);

	// public List<ProductResponseDto> getProducts();

	public Slice<ProductsResponseDto> getProducts(int page, int size);

	public ProductResponseDto getProduct(String productUuid);

	public List<ProductInfoResponseDto> getProductsInfo(String productSearchInfo);

	public ProductDetailsPriceResponseDto getProductPrice(String productUuid);

	public ProductFlagsResponseDto getProductFlags(String productUuid);

	public DiscountResponseDto getDiscountInfo(String productUuid);

	public void updateProduct(ProductRequestDto productRequestDto);

	public void deleteProduct(String uuid);

}

package starbucks3355.starbucksServer.domainProduct.service;

import org.springframework.data.domain.Slice;

import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountPriceResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountRateResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsPriceResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductFlagsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;

public interface ProductService {
	public void addProduct(ProductRequestDto productRequestDto);

	// public List<ProductResponseDto> getProducts();

	public Slice<ProductResponseDto> getProducts(int page, int size);

	public ProductResponseDto getProduct(String productUuid);

	public ProductDetailsPriceResponseDto getProductPrice(String productUuid);

	public ProductFlagsResponseDto getProductFlags(String productUuid);

	public DiscountRateResponseDto getDiscountRateInfo(String productUuid);

	public DiscountPriceResponseDto getDiscountPriceInfo(String productUuid);

	public void updateProduct(ProductRequestDto productRequestDto);

	public void deleteProduct(String uuid);

}

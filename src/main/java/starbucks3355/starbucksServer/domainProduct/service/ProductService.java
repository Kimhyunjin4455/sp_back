package starbucks3355.starbucksServer.domainProduct.service;

import org.springframework.data.domain.Slice;

import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductFlagsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;

public interface ProductService {
	public void addProduct(ProductRequestDto productRequestDto);

	// public List<ProductResponseDto> getProducts();

	public Slice<ProductResponseDto> getProducts(int page, int size);

	public ProductResponseDto getProduct(String productUuid);

	public ProductDetailsResponseDto getProductDetails(String productUuid);

	public ProductFlagsResponseDto getProductFlags(String productUuid);

	public DiscountResponseDto getDiscountInfo(Long productCode);

	public void updateProduct(ProductRequestDto productRequestDto);

	public void deleteProduct(String uuid);

}

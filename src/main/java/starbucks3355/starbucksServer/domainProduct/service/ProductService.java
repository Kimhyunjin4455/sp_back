package starbucks3355.starbucksServer.domainProduct.service;

import java.util.List;

import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductFlagsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductImgResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;

public interface ProductService {
	public void addProduct(ProductRequestDto productRequestDto);

	public List<ProductResponseDto> getProducts();

	public ProductResponseDto getProduct(String productUuid);

	public ProductImgResponseDto getImage(String productUuid);

	public ProductDetailsResponseDto getProductDetails(String productUuid);

	public ProductFlagsResponseDto getProductFlags(String productUuid);

	public DiscountResponseDto getDiscountInfo(Long productCode);

	public void updateProduct(ProductRequestDto productRequestDto);

	public void deleteProduct(String uuid);

}

package starbucks3355.starbucksServer.domainProduct.service;

import java.util.List;

import starbucks3355.starbucksServer.domainProduct.dto.requestDto.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.responseDto.ProductResponseDto;

public interface ProductService {
	public void addProduct(ProductRequestDto productRequestDto);

	public List<ProductResponseDto> getProducts();

	ProductResponseDto getProduct(String productUuid);

	public void updateProduct(ProductRequestDto productRequestDto);

	public void deleteProduct(String uuid);

}

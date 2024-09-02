package starbucks3355.starbucksServer.domainProduct.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainProduct.dto.requestDto.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.responseDto.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.repository.ProductRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	public void addProduct(ProductRequestDto productRequestDto) {
		String productUuid;

		productUuid = UUID.randomUUID().toString();

		productRepository.save(productRequestDto.dtoToEntity(productUuid));
	}

	@Override
	public List<ProductResponseDto> getProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream()
			.map(product -> ProductResponseDto.builder()
				.productUuid(product.getProductUuid())
				.productName(product.getProductName())
				.productDescription(product.getProductDescription())
				.productInfo(product.getProductInfo())
				.build())
			.collect(Collectors.toList());
	}

	@Override
	public ProductResponseDto getProduct(String productUuid) {
		Product product = productRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		return ProductResponseDto.builder()
			.productUuid(product.getProductUuid())
			.productName(product.getProductName())
			.productDescription(product.getProductDescription())
			.productInfo(product.getProductInfo())
			.build();
	}

	@Override
	public void updateProduct(ProductRequestDto productRequestDto) {

	}

	@Override
	public void deleteProduct(String uuid) {

	}
}

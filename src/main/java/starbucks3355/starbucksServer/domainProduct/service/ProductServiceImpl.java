package starbucks3355.starbucksServer.domainProduct.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainProduct.dto.requestDto.ProductRequestDto;
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
	public List<Product> getProducts() {
		return null;
	}

	@Override
	public void updateProduct(ProductRequestDto productRequestDto) {

	}

	@Override
	public void deleteProduct(String uuid) {

	}
}

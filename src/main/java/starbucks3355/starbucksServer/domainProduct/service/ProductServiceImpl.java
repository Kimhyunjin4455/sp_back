package starbucks3355.starbucksServer.domainProduct.service;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsPriceResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductFlagsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductsResponseDto;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.repository.DiscountRepository;
import starbucks3355.starbucksServer.domainProduct.repository.FlagsRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductDetailsRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductListRepositoryCustom;
import starbucks3355.starbucksServer.domainProduct.repository.ProductRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductTagRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final DiscountRepository discountRepository;
	private final ProductDetailsRepository productDetailsRepository;
	private final FlagsRepository flagsRepository;
	private final ProductTagRepository productTagRepository;
	private final ProductListRepositoryCustom productListRepositoryCustom;

	@Override
	public void addProduct(ProductRequestDto productRequestDto) {
		Product product = productRequestDto.dtoToEntity(UUID.randomUUID().toString());
		productRepository.save(product);
	}

	@Override
	public Slice<ProductsResponseDto> getProducts(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Slice<Product> products = productRepository.findAll(pageable);

		return products.map(product -> ProductsResponseDto.from(product));
	}

	@Override
	public CursorPage<String> getProductList(Long lastId, Integer pageSize, Integer page) {
		return productListRepositoryCustom.getProductList(lastId, pageSize, page);
	}

	@Override
	public CursorPage<String> getSearchedProductList(String keyword, Long lastId, Integer pageSize, Integer page) {
		return productListRepositoryCustom.getSearchedProductList(keyword, lastId, pageSize, page);
	}

	@Override
	public ProductResponseDto getProduct(String productUuid) {

		return ProductResponseDto.from(productRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다.")));

	}

	@Override
	public ProductDetailsPriceResponseDto getProductPrice(String productUuid) {

		return ProductDetailsPriceResponseDto.from(productDetailsRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품정보가 존재하지 않습니다.")));

	}

	@Override
	public ProductFlagsResponseDto getProductFlags(String productUuid) {

		return ProductFlagsResponseDto.from(flagsRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품정보가 존재하지 않습니다.")));

	}

	@Override
	public DiscountResponseDto getDiscountInfo(String productUuid) {

		return DiscountResponseDto.from(discountRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 할인타입이 존재하지 않습니다.")));

	}

	@Override
	public void updateProduct(ProductRequestDto productRequestDto) {
		// 관리자 역할
	}

	@Override
	public void deleteProduct(String uuid) {
		// 관리자 역할
	}
}


package starbucks3355.starbucksServer.domainProduct.service;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductFlagsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDefaultDisCount;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDetails;
import starbucks3355.starbucksServer.domainProduct.entity.ProductFlags;
import starbucks3355.starbucksServer.domainProduct.repository.DiscountRepository;
import starbucks3355.starbucksServer.domainProduct.repository.FlagsRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductDetailsRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final DiscountRepository discountRepository;
	private final ProductDetailsRepository productDetailsRepository;
	private final FlagsRepository flagsRepository;

	@Override
	public void addProduct(ProductRequestDto productRequestDto) {
		String productUuid;

		productUuid = UUID.randomUUID().toString();

		productRepository.save(productRequestDto.dtoToEntity(productUuid));
	}

	@Override // 수정 필요
	public Slice<ProductResponseDto> getProducts(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Slice<Product> products = productRepository.findAll(pageable);

		return products.map(product -> ProductResponseDto.builder()
			.productUuid(product.getProductUuid())
			.productName(product.getProductName())
			.productDescription(product.getProductDescription())
			.productInfo(product.getProductInfo())
			.build());
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
	public ProductDetailsResponseDto getProductDetails(String productUuid) {

		ProductDetails productDetails = productDetailsRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품정보가 존재하지 않습니다."));

		return ProductDetailsResponseDto.builder()
			.productUuid(productDetails.getProductUuid())
			.price(productDetails.getProductPrice())
			.build();
	}

	@Override
	public ProductFlagsResponseDto getProductFlags(String productUuid) {
		// Q2. 여기는 상품에 대해 찜하기여부, 최신상품여부, 베스트 여부
		// 단순 Boolean 처리이니, 위 3가지 필드에 대해 짬하기 나 베스트 등의 구현에 대해 신경쓰지 말고 새로 엔티티 생성할지?
		ProductFlags productFlags = flagsRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품정보가 존재하지 않습니다."));

		return ProductFlagsResponseDto.builder()
			.isLiked(productFlags.isLiked())
			.isNew(productFlags.isNew())
			.isBest(productFlags.isBest())
			.build();
	}

	@Override
	public DiscountResponseDto getDiscountInfo(Long productCode) {
		ProductDefaultDisCount productDefaultDisCount = discountRepository.findById(productCode)
			.orElseThrow(() -> new IllegalArgumentException("해당 할인타입이 존재하지 않습니다."));
		return DiscountResponseDto.builder()
			.discountType(productDefaultDisCount.getDiscountType()) // Q3. enum 타입인데 알아서 DB에서 가져와 입력되는지?
			.value(productDefaultDisCount.getValue())
			.build();
	}

	@Override
	public void updateProduct(ProductRequestDto productRequestDto) {

	}

	@Override
	public void deleteProduct(String uuid) {

	}
}

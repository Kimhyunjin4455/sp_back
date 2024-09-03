package starbucks3355.starbucksServer.domainProduct.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDefaultDisCount;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDetails;
import starbucks3355.starbucksServer.domainProduct.entity.ProductImage;
import starbucks3355.starbucksServer.domainProduct.entity.Review;
import starbucks3355.starbucksServer.domainProduct.repository.DiscountRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ImageRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductDetailsRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ReviewRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ImageRepository imgRepository;
	private final ReviewRepository reviewRepository;
	private final DiscountRepository discountRepository;
	private final ProductDetailsRepository productDetailsRepository;

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

		String memberUuid;
		memberUuid = UUID.randomUUID().toString(); // Member entity 목데이터

		Product product = productRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		ProductDetails details = productDetailsRepository.findById(
			product.getId()).orElseThrow(() -> new IllegalArgumentException("해당 상품정보가 존재하지 않습니다."));

		ProductDefaultDisCount productDiscount = discountRepository.findById(
			product.getId()).orElseThrow(() -> new IllegalArgumentException("해당 할인타입이 존재하지 않습니다."));

		ProductImage productImage = imgRepository.findById(
			product.getId()).orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다."));

		Review review = reviewRepository.findByMemberUuidAndProductCode(
			memberUuid, product.getId()).orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));

		int mockReviewCount = 999;

		return ProductResponseDto.builder()
			.productUuid(product.getProductUuid())
			.productName(product.getProductName())
			.productDescription(product.getProductDescription())
			.productInfo(product.getProductInfo())
			.productCode(product.getId())
			.price(details.getProductPrice())
			.discountType(productDiscount.getDiscountType())
			.value(productDiscount.getValue())
			.productImg(productImage.getS3Url())
			.reviewScore(review.getScore())
			.reviewCount(mockReviewCount)
			.isLiked(true)
			.isBest(true)
			.isNew(true)
			.build();
	}

	@Override
	public void updateProduct(ProductRequestDto productRequestDto) {

	}

	@Override
	public void deleteProduct(String uuid) {

	}
}

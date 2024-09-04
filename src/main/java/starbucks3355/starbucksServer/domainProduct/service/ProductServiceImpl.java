package starbucks3355.starbucksServer.domainProduct.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductFlagsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductImgResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDefaultDisCount;
import starbucks3355.starbucksServer.domainProduct.entity.ProductDetails;
import starbucks3355.starbucksServer.domainProduct.entity.ProductImage;
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

		// Q0. 아래의 Mock uuid에 대해 컨트롤러에서 여러 서비스에 대해 uuid, id값을 줘야되니 컨트롤러에서 생성하는게 맞는건지?
		String memberUuid;
		memberUuid = UUID.randomUUID().toString(); // Member entity 목데이터

		Product product = productRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

		// Q1. 리뷰에 대해서는 따로 Service 파일을 생성할지?
		// Review review = reviewRepository.findByMemberUuidAndProductCode(
		// 	memberUuid, product.getId()).orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));

		// int mockReviewCount = 999;

		return ProductResponseDto.builder()
			.productUuid(product.getProductUuid())
			.productName(product.getProductName())
			.productDescription(product.getProductDescription())
			.productInfo(product.getProductInfo())
			.build();
	}

	// Q1.5 아래와 같이 쪼개어 생성된 getXXX 메서드들 만큼 Controller에는 @GetMapping으로 따로 처리하면 되는지?
	@Override
	public ProductImgResponseDto getImage(Long productCode) {
		ProductImage productImage = imgRepository.findById(productCode)
			.orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다."));
		return ProductImgResponseDto.builder()
			.productImgUrl(productImage.getS3Url())
			.build();
	}

	@Override
	public ProductDetailsResponseDto getProductDetails(Long productCode) {

		ProductDetails productDetails = productDetailsRepository.findById(productCode)
			.orElseThrow(() -> new IllegalArgumentException("해당 상품정보가 존재하지 않습니다."));

		return ProductDetailsResponseDto.builder()
			.productCode(productDetails.getProductCode())
			.price(productDetails.getProductPrice())
			.build();
	}

	@Override
	public ProductFlagsResponseDto getProductFlags(Long productCode) {
		// Q2. 여기는 상품에 대해 찜하기여부, 최신상품여부, 베스트 여부
		// 단순 Boolean 처리이니, 위 3가지 필드에 대해 짬하기 나 베스트 등의 구현에 대해 신경쓰지 말고 새로 엔티티 생성할지?
		return null;
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

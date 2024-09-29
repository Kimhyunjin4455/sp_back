package starbucks3355.starbucksServer.domainProduct.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainProduct.dto.request.ProductRequestDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.DiscountResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductDetailsPriceResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductFlagsResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductTagResponseDto;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductsResponseDto;
import starbucks3355.starbucksServer.domainProduct.entity.Product;
import starbucks3355.starbucksServer.domainProduct.repository.DiscountRepository;
import starbucks3355.starbucksServer.domainProduct.repository.FlagsRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductDetailsRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductListRepositoryCustom;
import starbucks3355.starbucksServer.domainProduct.repository.ProductRepository;
import starbucks3355.starbucksServer.domainProduct.repository.ProductTagRepositoryCustom;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final DiscountRepository discountRepository;
	private final ProductDetailsRepository productDetailsRepository;
	private final FlagsRepository flagsRepository;
	private final ProductListRepositoryCustom productListRepositoryCustom;
	private final RedisTemplate<String, String> redisTemplate;
	private final ProductTagRepositoryCustom productTagRepositoryCustom;

	@PersistenceContext
	private EntityManager entityManager;

	private static final String RECENTLY_VIEWED_PREFIX = "recently_viewed:";
	private static final int MAX_SIZE = 40;

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
		entityManager.clear();  // 영속성 컨텍스트 초기화
		log.info("상품 조회 요청: UUID = {}", productUuid); // 요청한 UUID 로그

		Optional<Product> productOptional = productRepository.findProductByUuidNative(productUuid);

		if (productOptional.isPresent()) {
			log.info("상품 조회 성공: {}", productOptional.get()); // 상품 정보 로그
			//log.info("ProductResponseDto: {}", productResponseDto);
			return ProductResponseDto.from(productOptional.get());
		} else {
			log.warn("상품 조회 실패: 존재하지 않는 UUID = {}", productUuid); // 존재하지 않는 경우 로그
			throw new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT);
		}

		// return ProductResponseDto.from(productRepository.findByProductUuid(productUuid)
		// 	.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT)));

	}

	@Override
	public ProductDetailsPriceResponseDto getProductPrice(String productUuid) {

		return ProductDetailsPriceResponseDto.from(productDetailsRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT)));

	}

	@Override
	public ProductFlagsResponseDto getProductFlags(String productUuid) {

		return ProductFlagsResponseDto.from(flagsRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT)));

	}

	@Override
	public DiscountResponseDto getDiscountInfo(String productUuid) {

		return DiscountResponseDto.from(discountRepository.findByProductUuid(productUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DISCOUNT)));

	}

	@Override
	public CursorPage<String> getRecentlyViewed(String memberUuid, Integer pageSize, Integer page) {
		String key = RECENTLY_VIEWED_PREFIX + memberUuid;
		List<String> pagedProducts = redisTemplate.opsForList().range(key, (page - 1) * pageSize, page * pageSize - 1);

		Long nextCursor = (pagedProducts != null && pagedProducts.size() == pageSize) ?
			Long.valueOf(pagedProducts.get(pageSize - 1)) : null;
		boolean hasNext = nextCursor != null;

		return CursorPage.<String>builder()
			.content(pagedProducts)
			.nextCursor(nextCursor)
			.hasNext(hasNext)
			.pageSize(pageSize)
			.page(page)
			.build();

	}

	@Override
	public List<ProductTagResponseDto> getTagList() {
		return productTagRepositoryCustom.getTagList();
	}

	@Override
	public void addRecentlyViewed(String memberUuid, String productUuid
	) {
		String key = RECENTLY_VIEWED_PREFIX + memberUuid;

		// 비회원의 경우, TTL을 설정하여 임시로 저장
		if (memberUuid.startsWith("temp:")) {
			redisTemplate.opsForList().remove(key, 0, productUuid); // 중복 제거
			redisTemplate.opsForList().leftPush(key, productUuid); // 추가
			redisTemplate.expire(key, 1, TimeUnit.DAYS); // 1일간 유지
		} else {
			// 회원의 경우 영구적으로 저장
			redisTemplate.opsForList().remove(key, 0, productUuid); // 중복 제거
			redisTemplate.opsForList().leftPush(key, productUuid); // 추가
			redisTemplate.expire(key, 30, TimeUnit.DAYS); // 30일간 유지
		}

		// 최대 크기 초과 시 가장 오래된 상품 제거
		if (redisTemplate.opsForList().size(key) > MAX_SIZE) {
			redisTemplate.opsForList().rightPop(key); // 가장 오래된 것 제거
		}
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


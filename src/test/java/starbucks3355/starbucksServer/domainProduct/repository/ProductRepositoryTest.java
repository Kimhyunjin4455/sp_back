package starbucks3355.starbucksServer.domainProduct.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainReview.repository.ReviewRepository;
import starbucks3355.starbucksServer.domainReview.repository.ReviewRepositoryCustom;

@SpringBootTest
@Slf4j
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductListRepositoryCustom productListRepositoryCustom;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ReviewRepositoryCustom reviewRepositoryCustom;
	// @Test
	// public void 상품더미데이터삽입() {
	//
	// 	long startTime = System.currentTimeMillis(); // 시작 시간 기록
	//
	// 	IntStream.rangeClosed(1, 5000000).forEach(i -> {
	// 		Product product = Product.builder()
	// 			.productUuid(UUID.randomUUID().toString())
	// 			.productName("b상품" + i)
	// 			.productDescription("상품상세설명" + i)
	// 			.productInfo("상품간단정보" + i)
	// 			.build();
	// 		productRepository.save(product);
	//
	// 	});
  
	// 	long endTime = System.currentTimeMillis(); // 종료 시간 기록
	// 	long duration = endTime - startTime; // 실행 시간 계산
	//
	// 	// 실행 시간 로그 출력
	// 	System.out.println("상품 더미 데이터 삽입 완료. 실행 시간: " + duration + "ms");
	//
	// }

	// @Test
	// public void 조회성능테스트() {
	// 	long startTime = System.currentTimeMillis(); // 시작 시간 기록
	// 	CursorPage<String> productList = productListRepositoryCustom.getProductList(5300000L, 20, 250000);
	// 	log.info(productList.toString());
	//
	// 	long endTime = System.currentTimeMillis(); // 종료 시간 기록
	// 	long duration = endTime - startTime; // 실행 시간 계산
	//
	// 	// 실행 시간 로그 출력
	// 	log.info("커서 방식 통한 상품 리스트 조회 완료. 실행 시간: " + duration + "ms");
	//
	// 	long startTimePaging = System.currentTimeMillis(); // 시작 시간 기록
	// 	Pageable pageable = PageRequest.of(250000, 20);
	// 	Slice<Product> products = productRepository.findAll(pageable);
	//
	// 	long endTimePaging = System.currentTimeMillis(); // 종료 시간 기록
	// 	long durationPaging = endTimePaging - startTimePaging; // 실행 시간 계산
	//
	// 	// 실행 시간 로그 출력
	// 	log.info("pageable 통한 상품 리스트 조회 완료. 실행 시간: " + durationPaging + "ms");
	// }

	// @Test
	// public void 검색성능비교테스트() {
	// 	long startTime = System.currentTimeMillis(); // 시작 시간 기록
	// 	CursorPage<String> searchedProductList = productListRepositoryCustom.getSearchedProductList("장우산", 20L, 20,
	// 		1);
	// 	log.info(searchedProductList.toString());

	//
	// 	long endTime = System.currentTimeMillis(); // 종료 시간 기록
	// 	long duration = endTime - startTime; // 실행 시간 계산
	//
	// 	// 실행 시간 로그 출력
	// 	log.info("커서 방식 통한 상품 리스트 조회 완료. 실행 시간: " + duration + "ms");
	//
	// 	long startTimePaging = System.currentTimeMillis(); // 시작 시간 기록
	// 	List<Product> products = productRepository.findByProductNameContaining("장우산");
	// 	long endTimePaging = System.currentTimeMillis(); // 종료 시간 기록
	// 	long durationPaging = endTimePaging - startTimePaging; // 실행 시간 계산
	//
	// 	// 실행 시간 로그 출력
	// 	log.info("pageable 통한 상품 리스트 조회 완료. 실행 시간: " + durationPaging + "ms");

	// 	System.out.println("상품 더미 데이터 삽입 완료. 실행 시간: " + duration + "ms");

	// }

	// 베스트 리뷰조회에 대해 jpa 와 querydsl로 구현한 것을 비교해보기

}

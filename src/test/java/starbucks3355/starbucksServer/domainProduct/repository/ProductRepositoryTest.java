package starbucks3355.starbucksServer.domainProduct.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ProductRepositoryTest {
  
	@Autowired
	private ProductRepository productRepository;

	// @Test
	// public void 상품더미데이터삽입() {

	// 	long startTime = System.currentTimeMillis(); // 시작 시간 기록
	//
	// 	IntStream.rangeClosed(1, 100000000).forEach(i -> {
	// 		Product product = Product.builder()
	// 			.productUuid(UUID.randomUUID().toString())
	// 			.productName("b상품" + i)
	// 			.productDescription("상품상세설명" + i)
	// 			.productInfo("상품간단정보" + i)
	// 			.build();
	// 		productRepository.save(product);

	// 	});

}



	// 	long endTime = System.currentTimeMillis(); // 종료 시간 기록
	// 	long duration = endTime - startTime; // 실행 시간 계산
	//
	// 	// 실행 시간 로그 출력
	// 	System.out.println("상품 더미 데이터 삽입 완료. 실행 시간: " + duration + "ms");
	//
	// }

}

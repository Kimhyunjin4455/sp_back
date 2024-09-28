package starbucks3355.starbucksServer.vendor.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.utils.CursorPage;

@SpringBootTest
@Slf4j
public class ProductListByPromotionRepositoryCustomTest {

	@Autowired
	ProductListByPromotionRepositoryCustom productListByPromotionRepositoryCustom;

	@Autowired
	ProductListByPromotionRepository productListByPromotionRepository;

	// @Test
	// public void 데이터삽입() {
	//
	// 	long startTime = System.currentTimeMillis(); // 시작 시간 기록
	//
	// 	IntStream.rangeClosed(1, 100000).forEach(i -> {
	// 		ProductByPromotionList product = ProductByPromotionList.builder()
	// 			.productUuid(UUID.randomUUID().toString())
	// 			// 1에서 5까지 랜덤한 숫자를 생성
	// 			.promotionUuid(String.valueOf(new Random().nextInt(5) + 1)).build();
	// 		productListByPromotionRepository.save(product);
	// 	});
	//
	// 	long endTime = System.currentTimeMillis(); // 종료 시간 기록
	// 	long duration = endTime - startTime; // 실행 시간 계산
	// 	log.info("데이터 삽입 완료. 실행 시간: " + duration + "ms");
	// }

	@Test
	public void 데이터조회() {

		long startTime = System.currentTimeMillis(); // 시작 시간 기록

		CursorPage<String> res = productListByPromotionRepositoryCustom
			.getProductByPromotionList("1", 10000L, 20, 900);

		long endTime = System.currentTimeMillis(); // 종료 시간 기록
		long duration = endTime - startTime; // 실행 시간 계산

		log.info("데이터 조회 완료. 실행 시간: " + duration + "ms");

	}

}

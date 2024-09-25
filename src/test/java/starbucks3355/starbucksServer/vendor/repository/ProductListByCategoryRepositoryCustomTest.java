package starbucks3355.starbucksServer.vendor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
	//@Log4sj
class ProductListByCategoryRepositoryCustomTest {
	@Autowired
	ProductListByCategoryRepositoryCustom productListByCategoryRepositoryCustom;

	// @Test
	// void ProductListByCategoryRepositoryCustom() {
	// 	List<CategoryAndCountOfSearchedProductListResponseDto> res = productListByCategoryRepositoryCustom
	// 		.getCategoryAndCountOfSearchedProductList(List.of(
	// 			"product table에 존재하는 상품uuid 목록들"));
	//
	// 	for (CategoryAndCountOfSearchedProductListResponseDto re : res) {
	// 		System.out.println("res : " + re);
	// 	}
	// }

}
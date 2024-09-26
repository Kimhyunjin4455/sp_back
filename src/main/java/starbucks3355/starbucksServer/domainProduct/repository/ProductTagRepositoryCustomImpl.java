package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainProduct.dto.response.ProductTagResponseDto;
import starbucks3355.starbucksServer.domainProduct.entity.QProductTag;

@RequiredArgsConstructor
@Repository
public class ProductTagRepositoryCustomImpl implements ProductTagRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<ProductTagResponseDto> getTagList() {
		QProductTag productTag = QProductTag.productTag;

		List<String> productTagList = jpaQueryFactory
			.selectDistinct(productTag.tagName)
			.from(productTag)
			.fetch();

		return productTagList.stream()
			.map(ProductTagResponseDto::fromName)
			.toList();
	}
}

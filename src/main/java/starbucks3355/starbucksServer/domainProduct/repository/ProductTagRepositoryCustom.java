package starbucks3355.starbucksServer.domainProduct.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainProduct.dto.response.ProductTagResponseDto;

@Repository
public interface ProductTagRepositoryCustom {
	List<ProductTagResponseDto> getTagList();
}

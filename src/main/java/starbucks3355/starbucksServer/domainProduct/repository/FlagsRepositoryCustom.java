package starbucks3355.starbucksServer.domainProduct.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface FlagsRepositoryCustom {
	// 등록 메서드
	public void saveFlags(String productUuid);
}

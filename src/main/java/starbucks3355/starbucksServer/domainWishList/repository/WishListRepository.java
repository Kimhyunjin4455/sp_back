package starbucks3355.starbucksServer.domainWishList.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainWishList.entity.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
	List<WishList> findByMemberUuid(String memberUuid);

	Optional<WishList> findByMemberUuidAndProductUuid(String memberUuid, String productUuid);

	void deleteByMemberUuidAndProductUuid(String memberUuid, String productUuid);

	void deleteByMemberUuid(String memberUuid);
}

package starbucks3355.starbucksServer.domainMember.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import starbucks3355.starbucksServer.domainMember.dto.LikesProductRequestDto;
import starbucks3355.starbucksServer.domainMember.entity.Likes;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainProduct.entity.Product;

public interface LikeProductRepository extends JpaRepository<Likes, Long> {
	List<Likes> findByUuid(String uuid);
	List<Likes> findByProductUuid(String productUuid);
	Optional<Likes> findByUuidAndProductUuid(String uuid, String productUuid);
}

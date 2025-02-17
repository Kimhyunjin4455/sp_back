package starbucks3355.starbucksServer.domainMember.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainMember.dto.LikesProductRequestDto;
import starbucks3355.starbucksServer.domainMember.entity.Likes;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainProduct.entity.Product;

@Repository
public interface LikeProductRepository extends JpaRepository<Likes, Long> {
	Optional<Likes> findByUuidAndProductUuid(String uuid, String productUuid);

	Slice<Likes> findByUuid(String uuid, Pageable pageable);

}

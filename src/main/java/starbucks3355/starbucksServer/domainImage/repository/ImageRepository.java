package starbucks3355.starbucksServer.domainImage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainImage.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByImageUuid(String imageUuid); // 이미지의 uuid로 호출할 일이 있을지 의문?

	Optional<Image> findByOtherUuid(String otherUuid);
}

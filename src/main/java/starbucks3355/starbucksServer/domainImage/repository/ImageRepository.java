package starbucks3355.starbucksServer.domainImage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import starbucks3355.starbucksServer.domainImage.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByImageUuid(String imageUuid); // 이미지의 uuid로 호출할 일이 있을지 의문?

	List<Image> findByOtherUuid(String otherUuid);

	Optional<Image> findByOtherUuidAndIsMainImage(String otherUuid, boolean isMainImage); // 상품에 대한 대표 이미지 호출

	void deleteByOtherUuid(String otherUuid);

	void deleteByImageNameAndOtherUuid(String imageName, String otherUuid);
}

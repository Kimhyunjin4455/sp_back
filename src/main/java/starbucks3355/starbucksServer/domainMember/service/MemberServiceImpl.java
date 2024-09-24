package starbucks3355.starbucksServer.domainMember.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainMember.dto.LikesProductResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberInfoResponseDto;
import starbucks3355.starbucksServer.domainMember.entity.Likes;
import starbucks3355.starbucksServer.domainMember.entity.LikesHistory;
import starbucks3355.starbucksServer.domainMember.repository.LikeProductRepository;
import starbucks3355.starbucksServer.domainMember.repository.LikeProductRepositoryCustom;
import starbucks3355.starbucksServer.domainMember.repository.LikesHistoryRepository;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final LikeProductRepository likeProductRepository;
	private final LikesHistoryRepository likesHistoryRepository;
	private final LikeProductRepositoryCustom likeProductRepositoryCustom;

	@Override
	public MemberInfoResponseDto getMemberInfo(String userUuid) {

		log.info("userUuid : {}", userUuid);
		return MemberInfoResponseDto.from(memberRepository.findByUuid(userUuid).orElseThrow(
			() -> new BaseException(BaseResponseStatus.NO_EXIST_USER)
		));
	}

	@Override
	public CursorPage<String> getLikesList(String userUuid, Long lastId, Integer pageSize, Integer page) {
		return likeProductRepositoryCustom.getLikesList(userUuid, lastId, pageSize, page);
	}

	@Override
	public LikesProductResponseDto LikeStatus(String uuid, String productUuid) {
		Optional<Likes> optionalLikes = likeProductRepository.findByUuidAndProductUuid(uuid, productUuid);

		Likes like = optionalLikes.orElseGet(() -> {
			// 찜이 되어 있지 않은 경우, 찜 추가
			Likes newLike = Likes.create(uuid, productUuid, true);
			likeProductRepository.save(newLike);
			likesHistoryRepository.save(LikesHistory.create(uuid, productUuid, true));
			return newLike;
		});

		// 찜이 되어 있는 경우, 찜 취소
		if (optionalLikes.isPresent()) {
			likeProductRepository.delete(like);
			likesHistoryRepository.save(LikesHistory.create(uuid, productUuid, false));
		}

		return new LikesProductResponseDto(like.getId(), like.getUuid(), like.getProductUuid(), !optionalLikes.isPresent());
	}

	@Override
	public LikesProductResponseDto checkLikeStatus(String uuid, String productUuid) {
		// 사용자의 찜 상태 조회
		Optional<Likes> optionalLikes = likeProductRepository.findByUuidAndProductUuid(uuid, productUuid);
		boolean liked = optionalLikes.isPresent();

		return LikesProductResponseDto.builder()
			.productUuid(productUuid)
			.isLiked(liked)
			.build();
	}

}



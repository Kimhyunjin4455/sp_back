package starbucks3355.starbucksServer.domainMember.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainMember.dto.LikesProductResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberInfoResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberReviewResponseDto;
import starbucks3355.starbucksServer.domainMember.entity.Likes;
import starbucks3355.starbucksServer.domainMember.entity.LikesHistory;
import starbucks3355.starbucksServer.domainMember.entity.Member;
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

		return new LikesProductResponseDto(like.getId(), !optionalLikes.isPresent());
	}

}



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
import starbucks3355.starbucksServer.domainMember.dto.LikesProductResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberInfoResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberReviewResponseDto;
import starbucks3355.starbucksServer.domainMember.entity.Likes;
import starbucks3355.starbucksServer.domainMember.entity.LikesHistory;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.repository.LikeProductRepository;
import starbucks3355.starbucksServer.domainMember.repository.LikesHistoryRepository;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final LikeProductRepository likeProductRepository;
	private final LikesHistoryRepository likesHistoryRepository;

	@Override
	public MemberInfoResponseDto getMemberInfo(String userUuid) {

		log.info("userUuid : {}", userUuid);
		return MemberInfoResponseDto.from(memberRepository.findByUuid(userUuid).orElseThrow(
			() -> new BaseException(BaseResponseStatus.NO_EXIST_USER)
		));
	}

	// 찜한 상품 목록 조회 (라스트 값 뽑기)
	@Override
	public Slice<LikesProductResponseDto> getLikesListByUuid(String uuid, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Slice<Likes> likes = likeProductRepository.findByUuid(uuid, pageable);

		return likes.map(like -> LikesProductResponseDto.builder()
				.id(like.getId())
				.uuid(like.getUuid())
				.productUuid(like.getProductUuid())
				.isLiked(like.isLiked())
				.build());
	}

	// 찜하기 여부 정하면서 history 쌓았음 (리팩토링 필요), else문 사용하지 않기
	@Override
	public LikesProductResponseDto LikeStatus(String uuid, String productUuid) {
		Optional<Likes> optionalLikes = likeProductRepository.findByUuidAndProductUuid(uuid, productUuid);

		if(optionalLikes.isPresent()) {
			// 찜이 되어 있는 경우, 찜 취소
			Likes like = optionalLikes.get();
			likeProductRepository.delete(like);

			// 찜 취소 이력 저장
			LikesHistory history = LikesHistory.builder()
				.uuid(uuid)
				.productUuid(productUuid)
				.isLiked(false)
				.timestamp(LocalDateTime.now())
				.build();
			likesHistoryRepository.save(history);
			return LikesProductResponseDto.builder()
				.id(like.getId())
				.uuid(like.getUuid())
				.productUuid(like.getProductUuid())
				.isLiked(false) // 취소된 경우 false
				.build();
		} else {
			// 찜이 되어 있지 않은 경우, 찜 추가
			Likes newLike = Likes.builder()
				.uuid(uuid)
				.productUuid(productUuid)
				.isLiked(true)
				.build();
			likeProductRepository.save(newLike);
			return LikesProductResponseDto.builder()
				.id(newLike.getId())
				.uuid(newLike.getUuid())
				.productUuid(newLike.getProductUuid())
				.isLiked(true) // 추가된 경우 true
				.build();
		}
	}

}



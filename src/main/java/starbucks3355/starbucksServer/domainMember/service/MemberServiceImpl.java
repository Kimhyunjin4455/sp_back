package starbucks3355.starbucksServer.domainMember.service;

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
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.repository.LikeProductRepository;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final LikeProductRepository likeProductRepository;

	@Override
	public MemberInfoResponseDto getMemberInfo(String userUuid) {

		log.info("userUuid : {}", userUuid);
		return MemberInfoResponseDto.from(memberRepository.findByUuid(userUuid).orElseThrow(
			() -> new BaseException(BaseResponseStatus.NO_EXIST_USER)
		));
	}

	@Override
	public MemberReviewResponseDto getNickname(String uuid) {
		// 데이터베이스에서 회원 정보 조회
		Optional<Member> optionalMember = memberRepository.findByUuid(uuid);

		// 회원이 존재하지 않을 경우 null 반환
		if (!optionalMember.isPresent()) {
			return null; // 또는 적절한 기본값을 반환할 수 있습니다.
		}

		// 회원 정보를 가져옴
		Member member = optionalMember.get();

		// MemberReviewResponseDto로 변환하여 반환
		return MemberReviewResponseDto.builder()
			.uuid(member.getUuid())
			.nickname(member.getNickname())
			.build();
	}

	@Override
	public Slice<LikesProductResponseDto> getLikesListByUuid(int page, int size) {
		return null;
	}

	// 찜한 상품 목록 조회
	// @Override
	// public Slice<LikesProductResponseDto> getLikesListByUuid(int page, int size) {
	// 	Pageable pageable = PageRequest.of(page, size);
	// 	Slice<Likes> likes = likeProductRepository.findAll(pageable);
	//
	// 	return likesList.map(likes -> LikesProductResponseDto.builder())
	// 			.id(likes.getId())
	// 			.uuid(likes.getUuid())
	// 			.productUuid(likes.getProductUuid())
	// 			.isLiked(likes.isLiked())
	// 			.build());
	// }

	// 찜하기 여부
	@Override
	public LikesProductResponseDto LikeStatus(String uuid, String productUuid) {
		Optional<Likes> optionalLikes = likeProductRepository.findByUuidAndProductUuid(uuid, productUuid);

		if(optionalLikes.isPresent()) {
			// 찜이 되어 있는 경우, 찜 취소
			Likes like = optionalLikes.get();
			likeProductRepository.delete(like);
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



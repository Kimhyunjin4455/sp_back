package starbucks3355.starbucksServer.domainMember.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public LikesProductResponseDto addLike(String uuid, String productUuid) {
		Likes like = Likes.builder()
			.uuid(uuid)
			.productUuid(productUuid)
			.isLikes(true) // 기본적으로 좋아요 추가할 때 true로 설정함
			.build();

		// 데이터베이스에 저장
		Likes savedLike = likeProductRepository.save(like);

		// LikesproductResponseDto로 변환하여 반환
		return LikesProductResponseDto.builder()
			.id(savedLike.getId())
			.uuid(savedLike.getUuid())
			.productUuid(savedLike.getProductUuid())
			.isLikes(savedLike.isLikes())
			.build();
	}


	@Override
	public List<LikesProductResponseDto> getLikesByUserUuid(String uuid) {

		List<Likes> likesList;
		try {
			likesList = likeProductRepository.findByUuid(uuid);
		} catch (Exception e) {
			throw new RuntimeException("데이터베이스 오류가 발생했습니다", e);
		}

		return likesList.stream()
			.map(like -> LikesProductResponseDto.builder()
				.id(like.getId())
				.uuid(like.getUuid())
				.productUuid(like.getProductUuid())
				.isLikes(like.isLikes())
				.build())
			.collect(Collectors.toList());
	}

}



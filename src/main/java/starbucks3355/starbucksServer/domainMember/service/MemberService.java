package starbucks3355.starbucksServer.domainMember.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainMember.dto.LikesProductResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberInfoResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberReviewResponseDto;
import starbucks3355.starbucksServer.domainMember.repository.LikeProductRepository;

@Service
public interface MemberService {
	MemberInfoResponseDto getMemberInfo(String userUuid);
	MemberReviewResponseDto getNickname(String memberUuid);
	LikesProductResponseDto addLike(String uuid, String productUuid);
	List<LikesProductResponseDto> getLikesByUserUuid(String uuid);

}


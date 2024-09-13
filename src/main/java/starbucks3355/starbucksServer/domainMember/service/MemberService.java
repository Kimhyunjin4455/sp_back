package starbucks3355.starbucksServer.domainMember.service;

import java.util.List;

import org.springframework.data.domain.Slice;
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
	// LikesProductResponseDto addLike(String uuid, String productUuid);
	Slice<LikesProductResponseDto> getLikesListByUuid(int page, int size);
	LikesProductResponseDto LikeStatus(String uuid, String productUuid);

}


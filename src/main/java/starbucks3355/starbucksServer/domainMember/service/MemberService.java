package starbucks3355.starbucksServer.domainMember.service;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.common.utils.CursorPage;
import starbucks3355.starbucksServer.domainMember.dto.LikesProductResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberInfoResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberReviewResponseDto;
import starbucks3355.starbucksServer.domainMember.repository.LikeProductRepository;

@Service
public interface MemberService {
	MemberInfoResponseDto getMemberInfo(String userUuid);
	// Slice<LikesProductResponseDto> getLikesListByUuid(String uuid);
	// public LikesProductResponseDto getLikesListByUuid(String uuid);
	CursorPage<String> getLikesList(
		String userUuid,
		Long lastId,
		Integer pageSize,
		Integer page
	);

	//public LikesProductResponseDto getLikes(String uuid);

	LikesProductResponseDto LikeStatus(String uuid, String productUuid);

	LikesProductResponseDto checkLikeStatus(String uuid, String productUuid);
}


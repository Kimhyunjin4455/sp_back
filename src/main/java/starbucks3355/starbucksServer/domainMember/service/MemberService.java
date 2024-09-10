package starbucks3355.starbucksServer.domainMember.service;

import org.springframework.stereotype.Service;

import starbucks3355.starbucksServer.domainMember.dto.MemberInfoResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberReviewResponseDto;

@Service
public interface MemberService {
	MemberInfoResponseDto getMemberInfo(String userUuid);
	MemberReviewResponseDto getNickname(String memberUuid);
}


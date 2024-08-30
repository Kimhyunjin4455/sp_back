package starbucks3355.starbucksServer.domainMember.service;

import org.springframework.stereotype.Service;

import starbucks3355.starbucksServer.domainMember.dto.requestDto.MemberRequestDto;
import starbucks3355.starbucksServer.domainMember.entity.Member;

@Service
public interface MemberService {
	public Member signUpMember(MemberRequestDto memberRequestDto);
}

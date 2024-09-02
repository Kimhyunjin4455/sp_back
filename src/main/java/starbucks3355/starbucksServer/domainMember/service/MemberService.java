package starbucks3355.starbucksServer.domainMember.service;

import org.springframework.stereotype.Service;

import starbucks3355.starbucksServer.domainMember.dto.requestDto.LoginRequestDto;
import starbucks3355.starbucksServer.domainMember.dto.requestDto.MemberRequestDto;
import starbucks3355.starbucksServer.domainMember.dto.reseponseDto.LoginResponseDto;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.repository.MemberRepository;

@Service
public interface MemberService {
	public Member signUpMember(MemberRequestDto memberRequestDto);
	LoginResponseDto loginMember(LoginRequestDto loginRequestDto);
}


package starbucks3355.starbucksServer.domainMember.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainMember.dto.requestDto.LoginRequestDto;
import starbucks3355.starbucksServer.domainMember.dto.requestDto.MemberRequestDto;
import starbucks3355.starbucksServer.domainMember.dto.reseponseDto.LoginResponseDto;
import starbucks3355.starbucksServer.domainMember.entity.Member;
import starbucks3355.starbucksServer.domainMember.service.MemberService;
import starbucks3355.starbucksServer.domainMember.vo.requestVo.MemberRequestVo;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
@Tag(name ="회원 관리", description = "회원 관리 API")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/signup")
	@Operation(summary = "회원가입")
	// vo의 값을 Dto로 변환
	// 변환된 값을 Service로 넘겨줌
	public ResponseEntity<Member> signUpMember(@RequestBody MemberRequestVo memberRequestVo) {
		MemberRequestDto memberRequestDto = new MemberRequestDto(
			memberRequestVo.getUserId(),
			memberRequestVo.getPassword(),
			memberRequestVo.getEmail(),
			memberRequestVo.getNickname()
		);
		memberService.signUpMember(memberRequestDto);
		return new ResponseEntity<Member>(HttpStatus.CREATED);
	}

	@PostMapping("/login")
	@Operation(summary = "로그인")
	public ResponseEntity<LoginResponseDto> loginMember(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
		// 로그인 서비스 호출하여 LoginResponseDto 객체 가져옴
		LoginResponseDto loginResponseDto = memberService.loginMember(loginRequestDto);

		// 로그인 성공시 세션에 사용자 정보 저장
		HttpSession session = request.getSession();
		session.setAttribute("user", loginRequestDto.getUserId()); // 예시로 사용자 ID 저장
		// 로그인 성공
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}

	@DeleteMapping("/logout")
	@Operation(summary = "로그아웃")
	public ResponseEntity<Void> logoutMember(HttpServletRequest request) {
		HttpSession session = request.getSession(false); // 현재 세션 가져오기 (없으면 null 반환)

		if (session != null) {
			memberService.logoutMember(session); // MemberService 호출
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401 Unauthorized
		}
	}



}

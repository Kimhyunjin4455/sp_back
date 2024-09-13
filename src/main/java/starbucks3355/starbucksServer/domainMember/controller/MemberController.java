package starbucks3355.starbucksServer.domainMember.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.auth.entity.AuthUserDetail;
import starbucks3355.starbucksServer.common.entity.BaseResponse;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.common.jwt.JwtTokenProvider;
import starbucks3355.starbucksServer.domainMember.dto.LikesProductResponseDto;
import starbucks3355.starbucksServer.domainMember.dto.MemberReviewResponseDto;
import starbucks3355.starbucksServer.domainMember.service.MemberService;
import starbucks3355.starbucksServer.domainMember.vo.LikesProductResponseVo;
import starbucks3355.starbucksServer.domainMember.vo.MemberInfoResponseVo;
import starbucks3355.starbucksServer.domainMember.vo.MemberReviewResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

	private final MemberService memberService;
	private final JwtTokenProvider provider;

	/**
	 * api/v1/member
	 * 1. 회원 정보 조회
	 * 2. 회원 정보 수정
	 * 3. 회원 탈퇴
	 */

	/**
	 * 회원 정보 조회
	 * @param authUserDetail
	 * @return
	 */

	@Operation(summary = "Member Get Info API", description = "Member 정보 조회 API", tags = {"Member"})
	@SecurityRequirement(name = "Bearer Auth")
	@GetMapping
	public BaseResponse<MemberInfoResponseVo> getMemberInfo(
		@AuthenticationPrincipal AuthUserDetail authUserDetail
	) {
		log.info("authUserDetail : {}", authUserDetail);
		log.info("authUserDetail.getUername() : {}", authUserDetail.getUsername());
		return new BaseResponse<>(
			memberService.getMemberInfo(authUserDetail.getUsername()
			).toVo()
		);
	}

	@GetMapping("/{uuid}")
	@Operation(summary = "회원 닉네임 조회")
	public CommonResponseEntity<MemberReviewResponseVo> getNickname(@PathVariable String uuid) {
		// MemberReviewResponseDto를 가져옴
		MemberReviewResponseDto nicknameDto = memberService.getNickname(uuid);

		// MemberReviewResponseVo로 변환
		MemberReviewResponseVo nicknameVo = nicknameDto.toVo();

		// CommonResponseEntity를 반환
		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			nicknameVo
		);
	}

	@PostMapping("/likes")
	@Operation(summary = "찜하기, 찜하기 취소")
	public ResponseEntity<LikesProductResponseDto> likeProduct(@RequestHeader("Authorization") String accessToken,
		String productUuid) {
		String uuid = provider.parseUuid(accessToken);

		if (uuid == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // UUID가 없을 경우
		}

		LikesProductResponseDto response = memberService.LikeStatus(uuid, productUuid);
		return ResponseEntity.ok(response);
	}

	// @GetMapping("/likeslist")
	// @Operation(summary = "찜한 상품 목록 조회")
	// public CommonResponseEntity<List<LikesProductResponseDto>> getLikesListByUuid(
	// 	@RequestHeader("Authorization") String accessToken,
	// 	@RequestParam(defaultValue = "0") int page,
	// 	@RequestParam(defaultValue = "20") int size
	// ) {
	// 	log.info("accesstoken : {}",accessToken);
	// 	String uuid = provider.parseUuid(accessToken);
	//
	// 	Slice<LikesProductResponseDto> likesProductResponseDtos = memberService.getLikesListByUuid(page, size);
	//
	// 	List<LikesProductResponseVo> likesProductResponseVos = likesProductResponseDtos.stream()
	// 		.map(LikesProductResponseDto::dtoToResponseVo)
	// 		.collect(Collectors.toList());
	//
	// 	return new CommonResponseEntity<>(
	// 		HttpStatus.OK,
	// 		CommonResponseMessage.SUCCESS.getMessage(),
	// 		likesProductResponseVos,
	// 		likesProductResponseDtos.hasNext()
	// 	);
	//
}

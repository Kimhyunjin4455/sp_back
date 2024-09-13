package starbucks3355.starbucksServer.domainWishList.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.CommonResponseEntity;
import starbucks3355.starbucksServer.common.entity.CommonResponseMessage;
import starbucks3355.starbucksServer.domainWishList.dto.in.WishListRequestDto;
import starbucks3355.starbucksServer.domainWishList.dto.out.WishListResponseDto;
import starbucks3355.starbucksServer.domainWishList.service.WishListService;
import starbucks3355.starbucksServer.domainWishList.vo.in.WishListRequestVo;
import starbucks3355.starbucksServer.domainWishList.vo.out.WishListResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/wishList")
@Tag(name = "WishList", description = "장바구니 API")
public class WishListController {
	private final WishListService wishListService;

	@GetMapping("/wishlist/{memberUuid}")
	@Operation(summary = " 나의 상품 장바구니 조회")
	public CommonResponseEntity<List<WishListResponseVo>> getMyWishList(
		@PathVariable String memberUuid) {
		List<WishListResponseDto> wishListRequestDtoList = wishListService.getMyWishListItems(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			wishListRequestDtoList.stream()
				.map(WishListResponseDto::dtoToResponseVo)
				.toList());

	}

	@PostMapping("/wishlist/{productUuid}/{memberUuid}/add")
	@Operation(summary = "상품 장바구니 추가")
	public CommonResponseEntity<Void> addProductToWishList(
		@RequestBody WishListRequestVo wishListRequestVo) {

		WishListRequestDto wishListRequestDto = WishListRequestDto.builder()
			.isChecked(wishListRequestVo.isChecked())
			.currentQuantity(wishListRequestVo.getCurrentQuantity())
			.limitQuantity(wishListRequestVo.getLimitQuantity())
			.productUuid(wishListRequestVo.getProductUuid())
			.memberUuid(wishListRequestVo.getMemberUuid())
			.regDate(wishListRequestVo.getRegDate())
			.modDate(wishListRequestVo.getModDate())
			.build();

		wishListService.addWishList(wishListRequestDto);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@DeleteMapping("/wishlist/{memberUuid}/{productUuid}/delete")
	@Operation(summary = "장바구니의 특정 품목 삭제")
	public CommonResponseEntity<Void> deleteProductFromWishList(
		@PathVariable String memberUuid,
		@PathVariable String productUuid) {

		wishListService.deleteWishList(memberUuid, productUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

	@DeleteMapping("/wishlist/{memberUuid}/delete")
	@Operation(summary = "장바구니 전체 삭제")
	public CommonResponseEntity<Void> deleteAllProductFromWishList(
		@PathVariable String memberUuid) {

		wishListService.deleteWishListAll(memberUuid);

		return new CommonResponseEntity<>(
			HttpStatus.OK,
			CommonResponseMessage.SUCCESS.getMessage(),
			null);
	}

}

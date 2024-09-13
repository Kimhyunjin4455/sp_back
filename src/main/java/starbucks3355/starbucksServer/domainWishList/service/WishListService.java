package starbucks3355.starbucksServer.domainWishList.service;

import java.util.List;

import starbucks3355.starbucksServer.domainWishList.dto.in.WishListRequestDto;
import starbucks3355.starbucksServer.domainWishList.dto.out.WishListResponseDto;

public interface WishListService {
	List<WishListResponseDto> getMyWishListItems(String memberUuid);

	void addWishList(WishListRequestDto wishListRequestDto);

	void deleteWishList(String memberUuid, String productUuid);

	void deleteWishListAll(String memberUuid);

	// 선택된 것들에 대한 삭제
	void deleteWishListChecked(String memberUuid);

	// 수량 업데이트
	void updateWishList(String memberUuid, String productUuid, int quantity);

	// 한 상품에 대한 체크 여부 변경
	void updateWishListCheck(String memberUuid, String productUuid, boolean isChecked);

	//모두 체크 여부 설정
	void updateWishListAllCheck(String memberUuid, boolean isChecked);

	//모두 체크 여부 해제
	void updateWishListAllUnCheck(String memberUuid);

	// 장바구니내에 memberUuid와 productUuid에 대해 둘 다 같은 값이 존재하지 않는 상품이면 추가, memberUuid와 productUuid 둘 다 같은 상품이 존재하면 그 상품의 currentQuantity를 증가시킴 (limitQuantity 이하까지 증가 가능), memberUuid에 대해 productUuid는 최대 20개까지 추가 가능
	// void addWishListIsExistProductInWishList(WishListRequestDto wishListRequestDto);

	// 상품 상세 페이지에서 선택한 어떤 상품 대해 그 상품 N개를 장바구니에 넣을때 상품이 존재하지 않으면 0+n이 limitQuantity 이하 까지 가능, 장바구니에 존재하면 currentQuantity를 n만큼 증가시킴 (limitQuantity 이하까지 증가 가능)
	void addWishListAtProductPage(WishListRequestDto wishListRequestDto, int quantity);

}

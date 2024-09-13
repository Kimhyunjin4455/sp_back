package starbucks3355.starbucksServer.domainWishList.service;

public interface WishListService {
	void addWishList(String userId, String productUuid);

	void deleteWishList(String userId, String productUuid);

	void deleteWishListAll(String userId);

	// 수량 업데이트
	void updateWishList(String userId, String productUuid, int quantity);

	// 한 상품에 대한 체크 여부 변경
	void updateWishListCheck(String userId, String productUuid, boolean isChecked);

	//모두 체크 여부 설정
	void updateWishListAllCheck(String userId, boolean isChecked);

	//모두 체크 여부 해제
	void updateWishListAllUnCheck(String userId);

	// 선택된 것에 대한 삭제
	void deleteWishListChecked(String userId);

}

package starbucks3355.starbucksServer.domainWishList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainWishList.dto.in.WishListRequestDto;
import starbucks3355.starbucksServer.domainWishList.dto.out.WishListResponseDto;
import starbucks3355.starbucksServer.domainWishList.entity.WishList;
import starbucks3355.starbucksServer.domainWishList.repository.WishListRepository;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
	private final WishListRepository wishListRepository;

	@Override
	public List<WishListResponseDto> getMyWishListItems(String memberUuid) {
		List<WishList> myWishList = wishListRepository.findByMemberUuid(memberUuid);

		if (myWishList != null) {
			return myWishList.stream()
				.map(myWishListItem -> WishListResponseDto.builder()
					.productUuid(myWishListItem.getProductUuid())
					.memberUuid(myWishListItem.getMemberUuid())
					.isChecked(
						myWishListItem.isChecked()) // Java의 Bean 규약에 따르면, boolean 타입 필드는 is 접두사를 사용하여 getter 메서드가 생성됨
					.limitQuantity(myWishListItem.getLimitQuantity())
					.currentQuantity(myWishListItem.getCurrentQuantity())
					.regDate(myWishListItem.getRegDate())
					.modDate(myWishListItem.getModDate())
					.build()).toList();
		}

		return List.of();
	}

	@Override
	public void addWishList(WishListRequestDto wishListRequestDto) {
		wishListRepository.save(
			wishListRequestDto.toEntity(wishListRequestDto.getProductUuid(), wishListRequestDto.getMemberUuid()));
	}

	@Override
	public void deleteWishList(String userId, String productUuid) {

	}

	@Override
	public void deleteWishListAll(String userId) {

	}

	@Override
	public void updateWishList(String userId, String productUuid, int quantity) {

	}

	@Override
	public void updateWishListCheck(String userId, String productUuid, boolean isChecked) {

	}

	@Override
	public void updateWishListAllCheck(String userId, boolean isChecked) {

	}

	@Override
	public void updateWishListAllUnCheck(String userId) {

	}

	@Override
	public void deleteWishListChecked(String userId) {

	}
}

package starbucks3355.starbucksServer.domainWishList.service;

import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.domainWishList.dto.in.WishListRequestDto;
import starbucks3355.starbucksServer.domainWishList.dto.out.WishListResponseDto;
import starbucks3355.starbucksServer.domainWishList.repository.WishListRepository;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
	private final WishListRepository wishListRepository;

	@Override
	public Slice<WishListResponseDto> getMyWishListItems(String memberUuid) {
		return null;
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

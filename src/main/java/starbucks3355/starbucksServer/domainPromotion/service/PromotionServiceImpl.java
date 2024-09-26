package starbucks3355.starbucksServer.domainPromotion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionNameResponseDto;
import starbucks3355.starbucksServer.domainPromotion.dto.out.PromotionResponseDto;
import starbucks3355.starbucksServer.domainPromotion.repository.PromotionRepositoryCustom;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
	private final PromotionRepositoryCustom promotionRepositoryCustom;

	@Override
	public List<PromotionResponseDto> getPromotionUuidList() {
		return promotionRepositoryCustom.getPromotionUuidList();

	}

	@Override
	public PromotionNameResponseDto getPromotionName(String promotionUuid) {
		return promotionRepositoryCustom.getPromotionName(promotionUuid);
	}

	@Override
	public List<PromotionNameResponseDto> getPromotionNameList() {
		return promotionRepositoryCustom.getPromotionNameList();
	}

	// @Override
	// public List<PromotionProductResponseDto> getPromotionProductList(String promotionUuid) {
	// 	return promotionRepositoryCustom.getPromotionProductList(promotionUuid);
	// }

}

package starbucks3355.starbucksServer.delivery.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import starbucks3355.starbucksServer.delivery.dto.request.DeliveryAddRequestDto;
import starbucks3355.starbucksServer.delivery.dto.response.DeliveryAllResponseDto;
import starbucks3355.starbucksServer.delivery.dto.response.DeliveryBaseResponseDto;
import starbucks3355.starbucksServer.delivery.entity.Delivery;
import starbucks3355.starbucksServer.delivery.repository.DeliveryRepository;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

	private final DeliveryRepository deliveryRepository;

	// 배송지 추가 생성
	@Override
	public void createAddDelivery(DeliveryAddRequestDto deliveryAddRequestDto) {

		if (deliveryRepository.existsByAddress(deliveryAddRequestDto.getAddress())) {
			throw new IllegalArgumentException("이미 등록된 주소입니다.");
		}
		Delivery delivery = deliveryAddRequestDto.toEntity();

		try {
			deliveryRepository.save(delivery);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalArgumentException("주소 등록에 실패했습니다.");
		}
	}

	// 모든 배송지 조회
	@Override
	public List<DeliveryAllResponseDto> getAllDelivery() {
		return deliveryRepository.findAll(
				Sort.by(Sort.Direction.ASC, "deliveryId"))
			.stream()
			.map(
				delivery -> DeliveryAllResponseDto.builder()
					.deliveryId(delivery.getDeliveryId())
					.nickname(delivery.getNickname())
					.address(delivery.getAddress())
					.detailAddress(delivery.getDetailAddress())
					.build())
			.toList();

	}

	// 기본 배송지 조회
	@Override
	public DeliveryBaseResponseDto getBaseDelivery(String uuid) {

		Delivery delivery = deliveryRepository.findBaseDelivery(uuid)
			.orElseThrow(() -> new IllegalArgumentException("기본 배송지가 없습니다. "));

		return DeliveryBaseResponseDto.builder()
			.deliveryId(delivery.getDeliveryId())
			.address(delivery.getAddress())
			.detailAddress(delivery.getDetailAddress())
			.build();

	}

}

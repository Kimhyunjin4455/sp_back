package starbucks3355.starbucksServer.shipping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingMemberRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingAllResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingListResponseDto;
import starbucks3355.starbucksServer.shipping.entity.ShippingAddress;
import starbucks3355.starbucksServer.shipping.entity.ShippingMemberAddress;
import starbucks3355.starbucksServer.shipping.repository.ShippingMemberRepository;
import starbucks3355.starbucksServer.shipping.repository.ShippingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingServiceImpl implements ShippingService {

	private final ShippingRepository shippingRepository;
	private final ShippingMemberRepository shippingMemberRepository;

	// 배송지 추가
	@Override
	public void createShipping(String memberUuid, ShippingAddRequestDto shippingAddRequestDto) {

		int shippingAddressCount = shippingRepository.countByUuid(memberUuid);

		if (shippingAddressCount >= 5) {
			throw new IllegalArgumentException("배송지는 최대 5개까지 등록 가능합니다.");
		}

		if (shippingRepository.existsByDetailAddressAndUuid(shippingAddRequestDto.getDetailAddress(), memberUuid)) {
			throw new IllegalArgumentException("이미 등록된 주소입니다.");
		}

		ShippingAddress shippingAddress = shippingAddRequestDto.toEntity(memberUuid, shippingAddRequestDto);

		try {
			shippingRepository.save(shippingAddress);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalArgumentException("주소 등록에 실패했습니다.");
		}
	}

	// 회원의 주소 등록
	@Override
	@Transactional
	public void enrollAddress(String uuid, ShippingMemberRequestDto shippingMemberRequestDto) {
		log.info("UUID: {}", uuid);
		log.info("Detail Address: {}", shippingMemberRequestDto.getDetailAddress().trim().toLowerCase());

		if (shippingMemberRepository.existsByUuid(uuid)) {
			throw new IllegalArgumentException("이미 등록된 주소입니다.");
		}

		ShippingMemberAddress shippingMemberAddress = shippingMemberRequestDto.toEntity(uuid);
		shippingMemberRepository.save(shippingMemberAddress);

		// baseAddress가 true인 경우 ShippingAddress에 저장
		if (shippingMemberRequestDto.isBaseAddress()) {
			ShippingAddress shippingAddress = ShippingAddress.builder()
				.address(shippingMemberRequestDto.getAddress())
				.detailAddress(shippingMemberRequestDto.getDetailAddress())
				.nickname(shippingMemberRequestDto.getNickName())
				.phone1(shippingMemberRequestDto.getPhone1())
				.phone2(shippingMemberRequestDto.getPhone2())
				.receiver(shippingMemberRequestDto.getReceiver())
				.message(shippingMemberRequestDto.getMessage())
				.uuid(uuid)
				.postNumber(shippingMemberRequestDto.getPostNumber())
				.baseAddress(true)
				.build();

			shippingRepository.save(shippingAddress);
		}
	}

	//배송지 목록 조회
	@Override
	public List<ShippingListResponseDto> getShippingList(String uuid) {
		List<ShippingAddress> shippingAddressList = shippingRepository.findByUuid(uuid);
		return shippingAddressList.stream()
			.map(
				shippingAddress -> ShippingListResponseDto.builder()
					.deliveryId(shippingAddress.getDeliveryId())
					.receiver(shippingAddress.getReceiver())
					.phone1(shippingAddress.getPhone1())
					.address(shippingAddress.getAddress())
					.detailAddress(shippingAddress.getDetailAddress())
					.build())
			.toList();
	}

	@Override
	public void deleteShipping(String uuid, Long deliveryId) {
		ShippingAddress shippingAddress = shippingRepository.findById(deliveryId)
			.orElseThrow(() -> new IllegalArgumentException("해당 배송지가 없습니다."));

		shippingRepository.deleteById(shippingAddress.getDeliveryId());
	}

	// 모든 배송지 조회
	@Override
	public List<ShippingAllResponseDto> getAllShippingAddress() {
		return shippingRepository.findAll(
				Sort.by(Sort.Direction.ASC, "deliveryId"))
			.stream()
			.map(
				shippingAddress -> ShippingAllResponseDto.builder()
					.deliveryId(shippingAddress.getDeliveryId())
					.nickname(shippingAddress.getNickname())
					.address(shippingAddress.getAddress())
					.detailAddress(shippingAddress.getDetailAddress())
					.build())
			.toList();

	}

	// 기본 배송지 조회
	@Override
	public ShippingBaseResponseDto getBaseShippingAddress(String uuid) {

		ShippingAddress shippingAddress = shippingRepository.findBaseDeliveryByUuid(uuid)
			.orElseThrow(() -> new IllegalArgumentException("기본 배송지가 없습니다. "));

		return ShippingBaseResponseDto.builder()
			.deliveryId(shippingAddress.getDeliveryId())
			.address(shippingAddress.getAddress())
			.detailAddress(shippingAddress.getDetailAddress())
			.build();

	}

	@Override
	@Transactional
	public void modifyBaseAddress(String uuid, Long deliveryId) {

		// 현재 사용자의 기본 배송지를 찾음
		// 옵셔널이라서 널 값이 들어올 수 있음
		Optional<ShippingMemberAddress> existingMemberAddress = shippingMemberRepository.findByUuidAndBaseAddressTrue(
			uuid);

		// 현재 기본 배송지로 돼 있는 값 false로 변경
		// 옵셔널이라서 널 값이 들어올 수 있음
		Optional<ShippingAddress> existingAddress = shippingRepository.findBaseDeliveryByUuid(uuid);

		// 기본 배송지 테이블에서 기본 배송지로 설정된 값이  false로 변경
		existingAddress.ifPresent(address -> {
			ShippingAddress updateAddress = ShippingAddress.builder()
				.deliveryId(address.getDeliveryId())
				.address(address.getAddress())
				.detailAddress(address.getDetailAddress())
				.nickname(address.getNickname())
				.postNumber(address.getPostNumber())
				.message(address.getMessage())
				.receiver(address.getReceiver())
				.phone1(address.getPhone1())
				.phone2(address.getPhone2())
				.uuid(address.getUuid())
				.baseAddress(false)
				.build();

			shippingRepository.save(updateAddress);

		});

		// 현재 회원의 주소에서 baseAddress 값 false 로 변경
		existingMemberAddress.ifPresent(address -> {
			ShippingMemberAddress updateMemberAddress = ShippingMemberAddress.builder()
				.memberAddressId(address.getMemberAddressId()) // memberId값 유지
				.address(address.getAddress())
				.detailAddress(address.getDetailAddress())
				.nickname(address.getNickname())
				.phone1(address.getPhone1())
				.phone2(address.getPhone2())
				.receiver(address.getReceiver())
				.postNumber(address.getPostNumber())
				.uuid(address.getUuid())
				.baseAddress(false) // 기본 주소 해제
				.build();

			shippingMemberRepository.save(updateMemberAddress);
		});

		// 변경할 deliveryId 값으로 해당 주소 찾기
		ShippingAddress shippingAddress = shippingRepository.findById(deliveryId)
			.orElseThrow(() -> new IllegalArgumentException("해당 배송지가 없습니다."));

		// 새로운 기본 배송지 값

		shippingRepository.save(ShippingAddress.builder()
			.deliveryId(shippingAddress.getDeliveryId())
			.address(shippingAddress.getAddress())
			.detailAddress(shippingAddress.getDetailAddress())
			.postNumber(shippingAddress.getPostNumber())
			.nickname(shippingAddress.getNickname())
			.receiver(shippingAddress.getReceiver())
			.message(shippingAddress.getMessage())
			.phone1(shippingAddress.getPhone1())
			.phone2(shippingAddress.getPhone2())
			.uuid(shippingAddress.getUuid())
			.baseAddress(true)
			.build());
		// 즉시 DB에 반영

	}

}

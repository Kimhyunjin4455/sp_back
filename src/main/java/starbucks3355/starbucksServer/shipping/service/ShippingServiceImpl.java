package starbucks3355.starbucksServer.shipping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;
import starbucks3355.starbucksServer.common.exception.BaseException;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingAddRequestDto;
import starbucks3355.starbucksServer.shipping.dto.request.ShippingModifyRequestDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingBaseResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingListResponseDto;
import starbucks3355.starbucksServer.shipping.dto.response.ShippingOneResponseDto;
import starbucks3355.starbucksServer.shipping.entity.ShippingAddress;
import starbucks3355.starbucksServer.shipping.entity.ShippingAgree;
import starbucks3355.starbucksServer.shipping.repository.ShippingAgreeRepository;
import starbucks3355.starbucksServer.shipping.repository.ShippingRepository;
import starbucks3355.starbucksServer.shipping.vo.response.ShippingAgreeResponseVo;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingServiceImpl implements ShippingService {

	private final ShippingRepository shippingRepository;
	private final ShippingAgreeRepository shippingAgreeRepository;

	// 배송지 추가
	@Override
	public void createShipping(String memberUuid, ShippingAddRequestDto shippingAddRequestDto) {

		int shippingAddressCount = shippingRepository.countByUuid(memberUuid);

		if (shippingAddressCount >= 6) {
			throw new BaseException(BaseResponseStatus.COUNT_OVER);  // 배송지 최대 개수 초과 예외
		}
		
		// base값이 true일때 -> false로 변경
		if (shippingAddRequestDto.isBaseAddress()) {
			// 원래 있던애 false 새로 등록하는애 true
			Optional<ShippingAddress> existingAddress = shippingRepository.findBaseAddressByUuid(memberUuid);
			existingAddress.ifPresent(shippingAddress -> {
				ShippingAddress updateAddress = ShippingAddress.builder()
					.deliveryId(shippingAddress.getDeliveryId())
					.address(shippingAddress.getAddress())
					.detailAddress(shippingAddress.getDetailAddress())
					.phone1(shippingAddress.getPhone1())
					.phone2(shippingAddress.getPhone2())
					.receiver(shippingAddress.getReceiver())
					.message(shippingAddress.getMessage())
					.nickname(shippingAddress.getNickname())
					.postNumber(shippingAddress.getPostNumber())
					.uuid(shippingAddress.getUuid())
					.baseAddress(false)
					.build();
				shippingRepository.save(updateAddress);
			});

			ShippingAddress newAddress = shippingRepository.save(
				ShippingAddress.builder()
					.nickname(shippingAddRequestDto.getNickname())
					.postNumber(shippingAddRequestDto.getPostNumber())
					.address(shippingAddRequestDto.getAddress())
					.detailAddress(shippingAddRequestDto.getDetailAddress())
					.phone1(shippingAddRequestDto.getPhone1())
					.phone2(shippingAddRequestDto.getPhone2())
					.message(shippingAddRequestDto.getMessage())
					.receiver(shippingAddRequestDto.getReceiver())
					.baseAddress(true)
					.uuid(memberUuid)
					.build());
		} else {
			// base값이 false일때???
			ShippingAddress newAddress = shippingRepository.save(
				ShippingAddress.builder()
					.nickname(shippingAddRequestDto.getNickname())
					.postNumber(shippingAddRequestDto.getPostNumber())
					.address(shippingAddRequestDto.getAddress())
					.detailAddress(shippingAddRequestDto.getDetailAddress())
					.phone1(shippingAddRequestDto.getPhone1())
					.phone2(shippingAddRequestDto.getPhone2())
					.message(shippingAddRequestDto.getMessage())
					.receiver(shippingAddRequestDto.getReceiver())
					.baseAddress(false)
					.uuid(memberUuid)
					.build());
			// 새로 등록하는애 false
			shippingRepository.save(newAddress);
		}
		// 지금 로직 true인 값이 무조건 있으면 -> false로 바뀜 => 스웨거에 false로 넣어도 디비 true인 값이면 그 값도 false로 바꿔버림
		// false로 넣어도 true인 값 유지하게

		// 새로운 배송지 추가 -> 기본 배송지로 설정

	}

	// 배송지 수정
	@Override
	public void modifyShipping(String memberUuid, Long deliveryId, ShippingModifyRequestDto shippingModifyRequestDto) {
		ShippingAddress shippingAddress = shippingRepository.findById(deliveryId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.SHIPPING_ID_NOT_EXIST));

		// base값이 true일때 -> false로 변경
		if (shippingModifyRequestDto.isBaseAddress()) {
			// 원래 있던애 false 새로 등록하는애 true
			Optional<ShippingAddress> existingAddress = shippingRepository.findBaseAddressByUuid(memberUuid);
			existingAddress.ifPresent(shippingAddress2 -> {
				ShippingAddress updateAddress = ShippingAddress.builder()
					.deliveryId(shippingAddress2.getDeliveryId())
					.address(shippingAddress2.getAddress())
					.detailAddress(shippingAddress2.getDetailAddress())
					.phone1(shippingAddress2.getPhone1())
					.phone2(shippingAddress2.getPhone2())
					.receiver(shippingAddress2.getReceiver())
					.message(shippingAddress2.getMessage())
					.nickname(shippingAddress2.getNickname())
					.postNumber(shippingAddress2.getPostNumber())
					.uuid(shippingAddress2.getUuid())
					.baseAddress(false)
					.build();
				shippingRepository.save(updateAddress);
			});

			ShippingAddress newAddress = shippingRepository.save(
				ShippingAddress.builder()
					.deliveryId(shippingAddress.getDeliveryId()) // 기존id 값 유지
					.nickname(shippingModifyRequestDto.getNickname())
					.postNumber(shippingModifyRequestDto.getPostNumber())
					.address(shippingModifyRequestDto.getAddress())
					.detailAddress(shippingModifyRequestDto.getDetailAddress())
					.phone1(shippingModifyRequestDto.getPhone1())
					.phone2(shippingModifyRequestDto.getPhone2())
					.message(shippingModifyRequestDto.getMessage())
					.receiver(shippingModifyRequestDto.getReceiver())
					.baseAddress(true)
					.uuid(memberUuid)
					.build());
		} else {
			// base값이 false일때???
			ShippingAddress newAddress = shippingRepository.save(
				ShippingAddress.builder()
					.deliveryId(shippingAddress.getDeliveryId()) // 기존id 값 유지
					.nickname(shippingModifyRequestDto.getNickname())
					.postNumber(shippingModifyRequestDto.getPostNumber())
					.address(shippingModifyRequestDto.getAddress())
					.detailAddress(shippingModifyRequestDto.getDetailAddress())
					.phone1(shippingModifyRequestDto.getPhone1())
					.phone2(shippingModifyRequestDto.getPhone2())
					.message(shippingModifyRequestDto.getMessage())
					.receiver(shippingModifyRequestDto.getReceiver())
					.baseAddress(false)
					.uuid(memberUuid)
					.build());
			// 새로 등록하는애 false
			shippingRepository.save(newAddress);
		}

	}

	@Override
	public ShippingOneResponseDto getShippingOne(String uuid, Long deliveryId) {

		ShippingAddress shippingAddress = shippingRepository.findById(deliveryId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.SHIPPING_ADDRESS_NOT_FOUND));

		return ShippingOneResponseDto.builder()
			.deliveryId(shippingAddress.getDeliveryId())
			.detailAddress(shippingAddress.getDetailAddress())
			.address(shippingAddress.getAddress())
			.nickname(shippingAddress.getNickname())
			.phone1(shippingAddress.getPhone1())
			.phone2(shippingAddress.getPhone2())
			.postNumber(shippingAddress.getPostNumber())
			.baseAddress(shippingAddress.isBaseAddress())
			.receiver(shippingAddress.getReceiver())
			.message(shippingAddress.getMessage())
			.build();

	}

	//배송지 목록 조회
	@Override
	@Transactional(readOnly = true)
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
					.nickname(shippingAddress.getNickname())
					.baseAddress(shippingAddress.isBaseAddress())
					.phone2(shippingAddress.getPhone2())
					.postNumber(shippingAddress.getPostNumber())
					.message(shippingAddress.getMessage())
					.build())
			.toList();
	}

	@Override
	public void deleteShipping(String uuid, Long deliveryId) {
		ShippingAddress shippingAddress = shippingRepository.findById(deliveryId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.SHIPPING_ADDRESS_NOT_FOUND));

		shippingRepository.deleteById(shippingAddress.getDeliveryId());

	}

	@Override
	@Transactional
	public void modifyBaseAddress(String uuid, Long deliveryId) {

		// 현재 기본 배송지로 돼 있는 값 false로 변경
		// 옵셔널이라서 널 값이 들어올 수 있음
		Optional<ShippingAddress> existingAddress = shippingRepository.findBaseAddressByUuid(uuid);

		// 1. 기존 기본 배송지를 찾아서 기본 배송지 해제
		// 2. 빌더를 통해 업데이트
		// 3. 저장
		existingAddress.ifPresent(shippingAddress -> {
			ShippingAddress updateAddress = ShippingAddress.builder()
				.deliveryId(shippingAddress.getDeliveryId())
				.address(shippingAddress.getAddress())
				.detailAddress(shippingAddress.getDetailAddress())
				.phone1(shippingAddress.getPhone1())
				.phone2(shippingAddress.getPhone2())
				.receiver(shippingAddress.getReceiver())
				.message(shippingAddress.getMessage())
				.nickname(shippingAddress.getNickname())
				.postNumber(shippingAddress.getPostNumber())
				.uuid(shippingAddress.getUuid())
				.baseAddress(false)
				.build();
			shippingRepository.save(updateAddress);
		});

		// 새로운 기본 배송지로 설정
		ShippingAddress newBaseAddress = shippingRepository.findById(deliveryId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.SHIPPING_ID_NOT_EXIST));

		shippingRepository.save(ShippingAddress.builder()
			.deliveryId(newBaseAddress.getDeliveryId())
			.address(newBaseAddress.getAddress())
			.detailAddress(newBaseAddress.getDetailAddress())
			.phone1(newBaseAddress.getPhone1())
			.phone2(newBaseAddress.getPhone2())
			.receiver(newBaseAddress.getReceiver())
			.message(newBaseAddress.getMessage())
			.nickname(newBaseAddress.getNickname())
			.postNumber(newBaseAddress.getPostNumber())
			.uuid(newBaseAddress.getUuid())
			.baseAddress(true)
			.build());
	}

	@Override
	public ShippingBaseResponseDto getBaseShippingAddress(String uuid) {

		ShippingAddress shippingAddress = shippingRepository.findBaseAddressByUuid(uuid)
			.orElseThrow(() -> new IllegalArgumentException("기본 배송지가 없습니다. "));

		return ShippingBaseResponseDto.builder()
			.deliveryId(shippingAddress.getDeliveryId())
			.address(shippingAddress.getAddress())
			.detailAddress(shippingAddress.getDetailAddress())
			.phone1(shippingAddress.getPhone1())
			.phone2(shippingAddress.getPhone2())
			.receiver(shippingAddress.getReceiver())
			.message(shippingAddress.getMessage())
			.nickname(shippingAddress.getNickname())
			.postNumber(shippingAddress.getPostNumber())
			.baseAddress(shippingAddress.isBaseAddress())
			.build();

	}

	private boolean agreeStatus = true;

	// 배송 정보 동의
	// 디폴트가 트루라고 할때 -> false로 바꾸기
	// 기본 배송지 토글
	@Override
	@Transactional
	public boolean agreeShippingCancel(String uuid) {
		List<ShippingAddress> shippingAddresses = shippingRepository.findByUuid(uuid);

		if (shippingAddresses == null || shippingAddresses.isEmpty()) {
			boolean currentStatus = agreeStatus;
			agreeStatus = !agreeStatus;
			return currentStatus;
		}

		boolean newAgreeStatus = false;
		// 기본 배송지 true일때 -> false로 변경
		for (ShippingAddress shippingAddress : shippingAddresses) {
			newAgreeStatus = !shippingAddress.isAgree();

			ShippingAddress updatedShippingAddress = ShippingAddress.builder()
				.deliveryId(shippingAddress.getDeliveryId())
				.address(shippingAddress.getAddress())
				.detailAddress(shippingAddress.getDetailAddress())
				.phone1(shippingAddress.getPhone1())
				.phone2(shippingAddress.getPhone2())
				.receiver(shippingAddress.getReceiver())
				.message(shippingAddress.getMessage())
				.nickname(shippingAddress.getNickname())
				.postNumber(shippingAddress.getPostNumber())
				.uuid(shippingAddress.getUuid())
				.baseAddress(shippingAddress.isBaseAddress())
				.agree(newAgreeStatus)
				.build();
			shippingRepository.save(updatedShippingAddress);
			// deleteShippingAddressByUuid(uuid);

			// 동의가 false일 경우, 배송지 삭제
			if (!newAgreeStatus) {
				deleteShippingAddressByUuid(uuid);
			}
		}
		return newAgreeStatus;
	}

	@Override
	public void deleteShippingAddressByUuid(String uuid) {
		shippingRepository.deleteAllByUuid(uuid);
	}

	@Override
	public boolean getAgreeStatus(String uuid) {
		agreeStatus = !agreeStatus;
		return agreeStatus;
	}

	// 배송지 약관 테이블의 토글
	@Override
	@Transactional
	public boolean toggleAgreeStatus(String uuid) {

		ShippingAgree shippingAgree = shippingAgreeRepository.findByUuid(uuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.SHIPPING_UUID_NOT_EXIST));

		// 현재 동의 상태를 토글
		boolean newAgreeStatus = !shippingAgree.isShippingAgree();

		// 빌더를 사용하여 기존 값을 복사하고 동의 상태만 변경
		ShippingAgree updatedShippingAgree = ShippingAgree.builder()
			.id(shippingAgree.getId())
			.uuid(shippingAgree.getUuid())
			.shippingAgree(newAgreeStatus)
			.build();

		// 변경된 상태 저장
		shippingAgreeRepository.save(updatedShippingAgree);

		// 동의가 false일 경우, 배송지 삭제
		if (!newAgreeStatus) {
			deleteShippingAddressByUuid(uuid);
		}

		return newAgreeStatus;
	}

	@Override
	public boolean getAgreeStatus2(String uuid) {
		agreeStatus = !agreeStatus;
		return agreeStatus;
	} // 조회를 해서 트루 뻘스 인 여부인지 -> 약관이 없으니까 -> 디폴트 값으로 uuid검색이 없을 경우 -> True 만들고 트루로 프론트에 보내준다

	@Override
	@Transactional
	public ShippingAgreeResponseVo getOrCreateAgreeStatus(String uuid) {

		Optional<ShippingAgree> existShippingAgree = shippingAgreeRepository.findByUuid(uuid);

		ShippingAgree shippingAgree;
		if (existShippingAgree.isEmpty()) {
			shippingAgree = ShippingAgree.builder()
				.uuid(uuid)
				.shippingAgree(true)
				.build();
			shippingAgree = shippingAgreeRepository.save(shippingAgree);
		} else {
			shippingAgree = existShippingAgree.get();
		}

		return ShippingAgreeResponseVo.builder()
			.shippingAgree(shippingAgree.isShippingAgree())
			.build();
	}
}

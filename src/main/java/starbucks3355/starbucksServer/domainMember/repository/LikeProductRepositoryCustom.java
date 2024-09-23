package starbucks3355.starbucksServer.domainMember.repository;

import starbucks3355.starbucksServer.common.utils.CursorPage;

public interface LikeProductRepositoryCustom {

	CursorPage<String> getLikesList(
		String userUuid,
		Long lastId,
		Integer pageSize,
		Integer page);
}

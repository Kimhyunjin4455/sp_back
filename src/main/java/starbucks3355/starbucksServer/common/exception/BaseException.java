package starbucks3355.starbucksServer.common.exception;

import lombok.Getter;
import starbucks3355.starbucksServer.common.entity.BaseResponseStatus;

@Getter
public class BaseException extends RuntimeException{

	private final BaseResponseStatus status;

	public BaseException(BaseResponseStatus status) {
		this.status = status;
	}
}
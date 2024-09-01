package starbucks3355.starbucksServer.domainOrders.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class OrderUpdateRequestDto {

    private UUID uuid;
}

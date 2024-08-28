package starbucks3355.starbucksServer.domainOrders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor

public class OrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private Integer totalAmount;
    private UUID uuid;
    private String userName;
    private String userPhoneNumber;
    private String userAddress;

    @Builder
    public OrderDto(Long id, LocalDateTime orderDate, Integer totalAmount, UUID uuid, String userName, String userPhoneNumber, String userAddress) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.uuid = uuid;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
    }
}

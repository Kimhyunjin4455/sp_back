package starbucks3355.starbucksServer.domainOrders.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 30)
    private Long order_id;
    @Column(nullable = false, length = 30)
    private LocalDateTime order_date;
    @Column(nullable = false, length = 40)
    private Integer total_amount;
    @Column(nullable = false, length = 100)
    private UUID uu_id;
    @Column(nullable = false, length = 30)
    private String user_name;
    @Column(nullable = false, length = 30)
    private String user_phoneNumber;
    @Column(nullable = false, length = 30)
    private String user_address;

}

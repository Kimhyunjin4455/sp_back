package starbucks3355.starbucksServer.domainOrders.entity;


import com.mysql.cj.protocol.ColumnDefinition;
import jakarta.persistence.*;
import jdk.incubator.vector.VectorOperators;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter

public class DelieveryState {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 20)
    private Integer state_info;
    @Column(columnDefinition="BINARY(16)")
    private byte uu_id;
}

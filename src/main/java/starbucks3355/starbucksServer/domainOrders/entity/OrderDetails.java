package starbucks3355.starbucksServer.domainOrders.entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Getter
@Table(name="order_details")
@NoArgsConstructor
public class OrderDetails {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 30)
    private Long order_detail_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Orders orders;

    @Column(nullable = false, length = 30)
    private Integer product_cnt;
    @Column(nullable = false, length = 30)
    private String product_code;
    @Column(nullable = false, length = 30)
    private Integer detail_price;
    @Column(nullable = false, length = 30)
    private String detail_name;
    @Column(nullable = false, length = 30)
    private String detail_color;
    @Column(nullable = false, length = 30)
    private String detail_size;
    @Column(nullable = false, length = 30)
    private String detail_engraving;

}

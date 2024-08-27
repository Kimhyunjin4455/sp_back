package starbucks3355.starbucksServer.domainProduct.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false, length = 100) // 100글자 제한
    private String productName;
    @Column(length = 300)
    private String productDescription;
    @Column(length = 10000)
    private String productInfo;


}

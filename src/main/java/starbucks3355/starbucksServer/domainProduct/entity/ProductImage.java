package starbucks3355.starbucksServer.domainProduct.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productImgId;
    @Column(nullable = false, length = 200)
    private String productImgName;
    @Column(nullable = false, length = 200)
    private String productImgPath;
    @Column(length = 250)
    private String thumbnailPath;
}

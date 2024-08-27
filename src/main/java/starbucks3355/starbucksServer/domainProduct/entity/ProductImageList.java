package starbucks3355.starbucksServer.domainProduct.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "productImage")
public class ProductImageList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailImgId;
    private String thumbnailPath;
    @Column(nullable = false)
    private Long productCode;
    @Column(nullable = false, length = 255)
    private String productDetailImgPath;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private ProductImage productImage;


}

package com.sparta.kreamclone_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// 신발 - 사이즈 , 옷 - 사이즈
// 신발사이즈와 옷 사이즈의 타입이 다르기 때문에 엔티티로 나눠 놓은 듯
@Getter
@Setter
@Entity(name = "TB_PRODUCTSIZE")
@NoArgsConstructor
public class ProductSize {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productSizeId;
    @Column(nullable = false)
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;

    public ProductSize(String size) {
        this.size = size;
    }
}

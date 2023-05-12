package com.sparta.kreamclone_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "TB_PRODUCTIMG")
@NoArgsConstructor
public class ProductImg {
    @Id
    @Column(name = "productImg", nullable = false, unique = true)
    private Long productImgId;

    @Column(nullable = false)
    private String productImgUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    public ProductImg(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }
}

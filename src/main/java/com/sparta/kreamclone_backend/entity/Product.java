package com.sparta.kreamclone_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "TB_PRODUCT")
@NoArgsConstructor
public class Product extends Timestamped{
    @Id
    @Column(name = "productId", nullable = false, unique = true)
    private Long productId;

    @Column(nullable = false)
    private String productEngNm;

    @Column(nullable = false)
    private String productKorNm;

    @Column(nullable = false)
    private Long productNum;

    @Column(nullable = false)
    private Long productPrice;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ProductSize> productSizeList = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Categories> categoriesList = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Brand> brandList = new ArrayList<>();

    public Product(String productEngNm, String productKorNm, Long productNum, Long productPrice) {
        this.productEngNm = productEngNm;
        this.productKorNm = productKorNm;
        this.productNum = productNum;
        this.productPrice = productPrice;
    }
}

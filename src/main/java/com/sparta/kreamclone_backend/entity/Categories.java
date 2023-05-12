package com.sparta.kreamclone_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
// 신발, 옷 가방 같은 것들이 카테고리
@Getter
@Entity(name="TB_CATEGOFIRES")
@NoArgsConstructor
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String categories_id ;

    @Column
    private String categoriesNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;


    public Categories(String categoriesNm) {
        this.categoriesNm = categoriesNm;
    }
}

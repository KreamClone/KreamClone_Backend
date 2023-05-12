package com.sparta.kreamclone_backend.dto;

import com.sparta.kreamclone_backend.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDto {
    private String productEngNm;
    private String productKorNm;
    private Long productNum;
    private Long productPrice;
    private List<ProeductSizesDto> proeductSizesDtoList;
    private List<CategoriesDto> categoriesDtoList;
    private List<BrandDto> brandDtoList;

    public ProductResponseDto(Product product) {
        this.productEngNm = product.getProductEngNm();
        this.productKorNm = product.getProductKorNm();
        this.productNum = product.getProductNum();
        this.productPrice = product.getProductPrice();
    }
}

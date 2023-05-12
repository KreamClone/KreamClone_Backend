package com.sparta.kreamclone_backend.controller;

import com.sparta.kreamclone_backend.dto.ProductRequestDto;
import com.sparta.kreamclone_backend.dto.ProductResponseDto;
import com.sparta.kreamclone_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.sparta.kreamclone_backend.security.UserDetailsImpl;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;


    //브랜드 상관없이 전체 상품 목록 조회
    @GetMapping("/products")
    public ResponseEntity<ProductResponseDto> getProducts(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ProductResponseDto productResponseDto = productService.getProducts();
        if(productResponseDto != null) {
            return ResponseEntity.ok().body(productResponseDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //브랜드별 선택해서 전체 상품 목록 조회
    @GetMapping("/productsBrand")
    public ResponseEntity<ProductResponseDto> getBrandProductsList(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        ProductResponseDto productResponseDto = productService.getBrandProductsList();
        if(productResponseDto != null) {
            return ResponseEntity.ok().body(productResponseDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }



    //브랜드별 - 상품카테고리별 조회
    //상품 선택 조회
    @GetMapping("/brand")
    public ResponseEntity<ProductResponseDto> getBrandProducts(@RequestBody ProductRequestDto requestDto, UserDetailsImpl userDetails) {
        ProductResponseDto productResponseDto = productService.getBrandProducts();
        if (productResponseDto != null) {
            return ResponseEntity.ok().body(productResponseDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/brand/detail/product")
    public ResponseEntity<ProductResponseDto> getBrandDetailProducts (@RequestBody ProductRequestDto requestDto, UserDetailsImpl userDetails) {
        ProductResponseDto productResponseDto = productService.getBrandDetailProducts();
        if (productResponseDto != null) {
            return ResponseEntity.ok().body(productResponseDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}

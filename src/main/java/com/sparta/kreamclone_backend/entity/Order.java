package com.sparta.kreamclone_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name="Order")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private Long orderSeller;

    @Column(nullable = false)
    private Long orderBuyer;

    @Column(nullable = false)
    private Long orderPrice;

    @Column(nullable = false)
    private String oderStatus;


    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ProductSize> productSizeList = new ArrayList<>();

    public Order(Long orderSeller, Long orderBuyer, Long orderPrice, String oderStatus) {
        this.orderSeller = orderSeller;
        this.orderBuyer = orderBuyer;
        this.orderPrice = orderPrice;
        this.oderStatus = oderStatus;
    }
}

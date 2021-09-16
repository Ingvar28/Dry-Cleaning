package ru.nosov.dry_cleaning.entities;


import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Entity(name = "Item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_item")
@ToString(callSuper = true)
public class ItemEntity extends AbstractEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_cart_id", nullable = false, updatable = false)
    private OrderEntity order;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clothes_category_id", nullable = false)
    private ClothesCategoryEntity clothesCategory;

    private String material;
    private String wash;
    private String squeezeOut;
    private String dry;
    private String iron;
    private String white;
    private String dryCleaning;



}

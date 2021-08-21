package ru.nosov.dry_cleaning.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity(name = "Item")
@Getter
@Setter
@ToString
@Table(name = "item")
public class ItemEntity{

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.EAGER)
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

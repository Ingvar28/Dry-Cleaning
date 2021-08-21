package ru.nosov.dry_cleaning.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "ClothesCategory")
@Getter
@Setter
@ToString
@Table(name = "clothes_category")
public class ClothesCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String size;
    private double price;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<ItemEntity> items;


}

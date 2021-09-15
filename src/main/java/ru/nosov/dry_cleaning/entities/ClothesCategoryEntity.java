package ru.nosov.dry_cleaning.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity(name = "ClothesCategory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clothes_category")
public class ClothesCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String size;
    private BigDecimal price;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<ItemEntity> items;


}

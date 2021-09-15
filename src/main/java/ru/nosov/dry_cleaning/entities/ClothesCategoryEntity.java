package ru.nosov.dry_cleaning.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity(name = "ClothesCategory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clothes_category")
@ToString(callSuper = true)
public class ClothesCategoryEntity extends AbstractEntity {

    private String category;
    private String size;
    private BigDecimal price;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<ItemEntity> items;


}

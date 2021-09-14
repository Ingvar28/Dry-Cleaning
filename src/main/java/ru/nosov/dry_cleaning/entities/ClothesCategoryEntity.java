package ru.nosov.dry_cleaning.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity(name = "ClothesCategory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clothes_category")
@ToString(callSuper = true)
public class ClothesCategoryEntity extends AbstractEntity{

    private String category;
    private String size;
    private BigDecimal price;

    @ToString.Exclude
    @OneToMany(mappedBy = "clothesCategory")
    private Set<ItemEntity> items;

}

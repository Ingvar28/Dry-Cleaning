package ru.nosov.dry_cleaning.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity(name = "ServiceType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_type")
public class ServiceTypeEntity extends AbstractEntity {



    private String serviceType;
    private BigDecimal price;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<OrderEntity> orders;



}

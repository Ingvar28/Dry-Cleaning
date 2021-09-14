package ru.nosov.dry_cleaning.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity(name = "ServiceType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_type")
@ToString(callSuper = true)
public class ServiceTypeEntity extends AbstractEntity{

    private String serviceType;
    private BigDecimal price;


    @ToString.Exclude
    @OneToMany(mappedBy = "service")
    private Set<OrderEntity> orders;



}

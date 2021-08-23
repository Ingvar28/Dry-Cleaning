package ru.nosov.dry_cleaning.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity(name = "ServiceType")
@Getter
@Setter
@ToString
@Table(name = "service_type")
public class ServiceTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private BigDecimal price;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<OrderEntity> orders;



}

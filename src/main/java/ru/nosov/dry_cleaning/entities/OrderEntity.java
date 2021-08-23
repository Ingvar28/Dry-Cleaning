package ru.nosov.dry_cleaning.entities;



import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderStartTime;
    private LocalDateTime orderEndTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClientEntity client;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

//    @OneToMany(fetch = FetchType.EAGER)
//    private Set<ItemEntity> items;


    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "service_id")
    private ServiceTypeEntity service;

    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeeEntity employee;

    private String orderStatus;



}

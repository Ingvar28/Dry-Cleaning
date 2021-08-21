package ru.nosov.dry_cleaning.entities;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "Order")
@Getter
@Setter
@ToString
@Table(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderStartTime;
    private LocalDateTime orderEndTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<ItemEntity> items;


    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "service_id")
    private ServiceTypeEntity service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    private String orderStatus;



}

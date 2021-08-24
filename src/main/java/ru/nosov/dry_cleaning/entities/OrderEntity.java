package ru.nosov.dry_cleaning.entities;



import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "Order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime orderStartTime;
    private LocalDateTime orderEndTime;

    @ManyToOne(fetch = FetchType.EAGER)
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
    private EmployeeEntity employee;

    private String orderStatus;



}

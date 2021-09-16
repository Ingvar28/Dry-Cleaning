package ru.nosov.dry_cleaning.entities;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_cart")
@ToString(callSuper = true)
public class OrderEntity extends AbstractEntity {

    @CreatedDate()
    @Column(name = "order_Start_Time", updatable = false)
    private LocalDateTime orderStartTime;

    private LocalDateTime orderEndTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @Nullable
    private ClientEntity client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", nullable = false)
    @Nullable
    private PaymentEntity payment;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, orphanRemoval = true)
    @Nullable
    private List<ItemEntity> items;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_type_id", nullable = false)
    @Nullable
    private ServiceTypeEntity service;

    @ManyToOne(fetch = FetchType.EAGER)
    @Nullable
    private EmployeeEntity employee;


    private String orderStatus;


}

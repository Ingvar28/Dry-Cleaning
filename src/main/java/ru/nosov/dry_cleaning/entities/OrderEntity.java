package ru.nosov.dry_cleaning.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "dry_cleaning_order")
@ToString(callSuper = true)
public class OrderEntity  extends AbstractEntity{

    @CreatedDate()
    @Column(name = "order_start_time", updatable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime orderStartTime;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime orderEndTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClientEntity client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", nullable = false)
    private PaymentEntity payment;


    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER)
    private Set<ItemEntity> items;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_type_id", nullable = false)
    private ServiceTypeEntity service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;


    private String orderStatus;



}

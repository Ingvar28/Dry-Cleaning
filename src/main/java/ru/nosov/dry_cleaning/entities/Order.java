package ru.nosov.dry_cleaning.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderStartTime;
    private LocalDateTime orderEndTime;

    @ManyToOne
    @ToString.Exclude
    private Long clientId;

    @OneToOne
    @ToString.Exclude
    private Long paymentId;

    @OneToMany

    @ToString.Exclude
    private Long clothesId;

    @OneToOne
    @ToString.Exclude
    private Long serviceId;

    @OneToOne
    @ToString.Exclude
    private Long employeeId;

    private String orderStatus;



}

package ru.nosov.dry_cleaning.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "Payment")
@Getter
@Setter
@ToString
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentMethod;
    private String status;

    @ManyToMany
    @JoinTable(name = "payment_employee",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<EmployeeEntity> employee;
}

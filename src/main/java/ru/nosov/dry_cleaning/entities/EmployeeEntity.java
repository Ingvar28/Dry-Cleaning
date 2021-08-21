package ru.nosov.dry_cleaning.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Employee")
@Getter
@Setter
@ToString
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    private PositionEntity position;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<OrderEntity> orders;

    @ManyToMany
    @JoinTable(name = "payment_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id"))
    private Set<PaymentEntity> payment;

}

package ru.nosov.dry_cleaning.entities;


import lombok.*;

import javax.persistence.*;


@Entity(name = "Payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
@ToString(callSuper = true)
public class PaymentEntity  extends AbstractEntity{

    private String paymentMethod;
    private String status;


    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}

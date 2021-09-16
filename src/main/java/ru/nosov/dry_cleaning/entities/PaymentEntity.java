package ru.nosov.dry_cleaning.entities;


import lombok.*;
import org.springframework.lang.Nullable;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    @Nullable
    private EmployeeEntity employee;
}

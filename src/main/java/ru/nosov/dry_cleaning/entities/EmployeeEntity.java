package ru.nosov.dry_cleaning.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@ToString(callSuper = true)
public class EmployeeEntity extends AbstractEntity{


    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    private PositionEntity position;

//    @OneToMany(fetch = FetchType.LAZY)//
//    private Set<OrderEntity> orders;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<PaymentEntity> payments;

}

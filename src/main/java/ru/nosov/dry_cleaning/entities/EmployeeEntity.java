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
public class EmployeeEntity  extends AbstractEntity{

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;


    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    private PositionEntity position;

    @ToString.Exclude
    @OneToMany(mappedBy = "employee")
    private Set<OrderEntity> orders;

    @ToString.Exclude
    @OneToMany(mappedBy = "employee")
    private Set<PaymentEntity> payments;

}

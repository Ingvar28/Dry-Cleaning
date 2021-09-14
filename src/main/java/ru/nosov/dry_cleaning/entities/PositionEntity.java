package ru.nosov.dry_cleaning.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
@ToString(callSuper = true)
public class PositionEntity extends AbstractEntity{

    private String jobTitle;
    private String duties;

    @ToString.Exclude
    @JoinColumn(name = "position_id")
    @OneToMany
    private Set<EmployeeEntity> employee;
}

package ru.nosov.dry_cleaning.entities;


import lombok.*;

import javax.persistence.*;

@Entity(name = "Position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
@ToString(callSuper = true)
public class PositionEntity  extends AbstractEntity{


    private String jobTitle;
    private String duties;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<EmployeeEntity> employees;
}

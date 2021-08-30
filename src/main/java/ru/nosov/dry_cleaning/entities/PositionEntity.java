package ru.nosov.dry_cleaning.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String duties;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<EmployeeEntity> employees;
}

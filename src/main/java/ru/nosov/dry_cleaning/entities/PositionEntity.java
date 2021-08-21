package ru.nosov.dry_cleaning.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Position")
@Getter
@Setter
@ToString
@Table(name = "position")
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String duties;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<EmployeeEntity> employees;
}

package ru.nosov.dry_cleaning.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
public class tagCare {

    @Id
    @OneToMany
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long Id;

    private String material;
    private String wash;
    private String squeezeOut;
    private String dry;
    private String iron;
    private String white;
    private String dryCleaning;

}

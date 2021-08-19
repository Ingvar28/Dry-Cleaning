package ru.nosov.dry_cleaning.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@ToString
public class clothesTagCare {

    @ManyToOne
    @ToString.Exclude
    private Long clothesId;

    @ManyToOne
    @ToString.Exclude
    private Long tagCareId;

}

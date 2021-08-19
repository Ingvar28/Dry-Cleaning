package ru.nosov.dry_cleaning.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String clientGrade;
    private String description;
    private Long orderId;
}

package ru.nosov.dry_cleaning.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "Client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String clientLevel;
    private String description;


    @OneToMany(fetch = FetchType.LAZY)
    private Set<OrderEntity> orders;
    //TODO finish OrderWebService



}

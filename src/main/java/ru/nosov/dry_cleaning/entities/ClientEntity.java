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
    @Column(length = 20, nullable = false)
    private String firstName;
    @Column(length = 40, nullable = false)
    private String lastName;
    @Column(length = 15, nullable = false)
    private String phone;
    @Column(length = 40, nullable = false)
    private String email;
    @Column(length = 40, nullable = false)
    private String clientLevel;
    private String description;


    @OneToMany(fetch = FetchType.LAZY)
    private Set<OrderEntity> orders;
    //TODO finish OrderWebService



}

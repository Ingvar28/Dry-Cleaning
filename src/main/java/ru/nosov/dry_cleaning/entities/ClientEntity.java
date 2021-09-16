package ru.nosov.dry_cleaning.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "client")
@ToString(callSuper = true)
public class ClientEntity  extends AbstractEntity{

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


    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<OrderEntity> orders = new ArrayList<>();



}

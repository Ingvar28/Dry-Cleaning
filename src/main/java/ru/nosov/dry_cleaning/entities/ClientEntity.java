package ru.nosov.dry_cleaning.entities;

import lombok.*;
import org.springframework.lang.Nullable;

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
    @ToString.Exclude
    @Nullable
    private List<OrderEntity> orderList = new ArrayList<>();

    public void addOrder(OrderEntity order) {
        orderList.add(order);
        order.setClient(this);
    }

    public void removeOrder(OrderEntity order) {
        orderList.remove(order);
        order.setClient(null);
    }

}

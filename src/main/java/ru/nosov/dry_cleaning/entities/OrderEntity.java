package ru.nosov.dry_cleaning.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "order_cart")
@ToString(callSuper = true)
public class OrderEntity extends AbstractEntity {

    @CreatedDate()
//    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "order_Start_Time", updatable = false)
    private LocalDateTime orderStartTime;

//    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime orderEndTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @Nullable
    private ClientEntity client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", nullable = false)
    @Nullable
    private PaymentEntity payment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_type_id", nullable = false)
    @Nullable
    private ServiceTypeEntity service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @Nullable
    private EmployeeEntity employee;

    private String orderStatus;


    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Nullable
    private List<ItemEntity> itemList;

    public void addItem(ItemEntity item) {
        itemList.add(item);
        item.setOrder(this);
    }

    public void removeItem(ItemEntity item) {
        itemList.remove(item);
        item.setOrder(null);
    }
}

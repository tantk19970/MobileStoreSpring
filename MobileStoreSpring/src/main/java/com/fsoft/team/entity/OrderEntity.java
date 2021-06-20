package com.fsoft.team.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblOrders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderID;

    @ManyToOne()
    @JoinColumn(name = "userName")
    private UserEntity user;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "totalPrice")
    private Float totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> listOrderDetail;

    public OrderEntity(UserEntity user, LocalDateTime orderDate, Float totalPrice) {
        this.user = user;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

}

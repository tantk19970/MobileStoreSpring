package com.fsoft.team.entity;

import com.fsoft.team.dtos.ProductDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblOrderDetails")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailID;
    
    @ManyToOne()
    @JoinColumn(name = "productID")
    private ProductEntity product;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "price")
    private Double price;
    
    @ManyToOne()
    @JoinColumn(name = "orderID")
    private OrderEntity order;

    public OrderDetailEntity(ProductEntity product, Integer quantity, Double price, OrderEntity order) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }
}

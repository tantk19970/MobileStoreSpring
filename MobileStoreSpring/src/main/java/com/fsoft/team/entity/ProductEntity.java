package com.fsoft.team.entity;

import java.util.List;
import javax.persistence.CascadeType;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tblProduct")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productID;
    private String productName;
    private double unitPrice;
    private int unitInStock;
    private String description;
    private String manufacturer;
    private String condition;
    private String imgProduct;
    private String categoryID;
    @ManyToOne
    @JoinColumn(name = "categoryID", insertable = false, updatable = false)
    public CategoryEntity categoryOfProduct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> listOrderDetail;
}

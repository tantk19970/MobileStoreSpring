package com.fsoft.team.entity;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tblProduct")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String productID;
	private String productName;
	private double unitPrice;
	private int unitInStock;
	private String description;
	private String manufacturer;
	private String category;
	private String condition;
	private String imgProduct;
	private String categoryID;
	@ManyToOne
	@JoinColumn(name = "categoryID", insertable = false, updatable = false)
	public CategoryEntity categoryOfProduct;
}

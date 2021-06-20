package com.fsoft.team.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private long productID;
	private String productName;
	private double unitPrice;
	private int unitInStock;
	private String description;
	private String manufacturer;
	private String condition;
	private String imgProduct;
	private String categoryID;
}

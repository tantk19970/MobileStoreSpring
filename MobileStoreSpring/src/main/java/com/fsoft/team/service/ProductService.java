package com.fsoft.team.service;

import com.fsoft.team.entity.ProductEntity;
import java.util.List;

public interface ProductService {

    public void addProduct(ProductEntity product);

    
    // Shopping

    int checkUnitsInStock(Long id);

    ProductEntity findProductByID(Long id);

    void updateUnitsInStock(Integer newUnitsInStock, Long id);
}

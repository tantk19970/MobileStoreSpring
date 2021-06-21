package com.fsoft.team.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.team.dtos.ProductDTO;
import com.fsoft.team.entity.ProductEntity;
import com.fsoft.team.repository.ProductRepository;
import com.fsoft.team.service.ProductService;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void addProduct(ProductEntity product) {
        productRepository.save(product);

    }

    @Override
    public int checkUnitsInStock(Long id) {
        return productRepository.checkUnitsInStock(id);
    }

    @Override
    public ProductEntity findProductByID(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void updateUnitsInStock(Integer newUnitsInStock, Long id) {
        ProductEntity product = productRepository.findById(id).get();
        product.setUnitInStock(newUnitsInStock);
        productRepository.save(product);
    }
    
    @Override
    public List<ProductEntity> getListByCategory(Long categoryId){
    	return productRepository.getListByCategory(categoryId);
    }
    

}
//new
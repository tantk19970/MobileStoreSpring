package com.fsoft.team.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.team.entity.ProductEntity;
import com.fsoft.team.repository.ProductRepository;
import com.fsoft.team.service.ProductService;
@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public void addProduct(ProductEntity product) {
		productRepository.save(product);
		
	}

}

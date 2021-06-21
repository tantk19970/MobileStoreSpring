package com.fsoft.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.team.dtos.ProductDTO;
import com.fsoft.team.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    
    //Shopping
    
    @Query("SELECT p.unitInStock FROM ProductEntity p WHERE p.productID = ?1")
    public int checkUnitsInStock(Long id);
    
    @Query("SELECT p.productID,p.productName FROM ProductEntity p WHERE p.categoryID= ?1")
    public List<ProductDTO> getListByCategory();
    //repo
}

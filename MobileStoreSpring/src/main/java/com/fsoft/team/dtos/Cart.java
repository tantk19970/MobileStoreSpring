package com.fsoft.team.dtos;

import com.fsoft.team.entity.ProductEntity;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Map<Long, ProductEntity> cart;

    public void add(ProductEntity product) {
        if (cart == null) {
            cart = new HashMap<>();
        }
        if (cart.containsKey(product.getProductID())) {
            int quantity = cart.get(product.getProductID()).getUnitInStock();
            product.setUnitInStock(quantity + 1);
        }
        cart.put(product.getProductID(), product);
    }

    public void delete(Long id) {
        if (cart == null) {
            return;
        }
        if (cart.containsKey(id)) {
            cart.remove(id);
        }
    }

    public void clear() {
        if (cart == null) {
            return;
        } else {
            cart.clear();
        }
    }

    public void update(ProductEntity product) {
        if (cart != null) {
            if (cart.containsKey(product.getProductID())) {
                cart.replace(product.getProductID(), product);
            }
        }
    }
}

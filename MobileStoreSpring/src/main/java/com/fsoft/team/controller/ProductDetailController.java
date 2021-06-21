package com.fsoft.team.controller;

import com.fsoft.team.dtos.Cart;
import com.fsoft.team.entity.ProductEntity;
import com.fsoft.team.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ProductDetailController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product-detail")
    public String detail(Model model, @RequestParam(value = "productID") long productID){
        model.addAttribute("productDetail", productRepository.findProductEntityByProductID(productID));
        return "/product/product-detail";
    }

    //Add to cart (from ProductDetail)
    @GetMapping("/product-detail/addToCart")
    public String addToCart(Model model, @RequestParam(value = "productID") long productId, HttpSession session){
        ProductEntity product = productRepository.findProductEntityByProductID(productId);
        Cart cart = (Cart) session.getAttribute("CART");
        if(cart == null){
            cart = new Cart();
        }
        cart.add(product);
        session.setAttribute("CART", cart);
        return "redirect:/product-detail?productID=" + productId ;
    }
}

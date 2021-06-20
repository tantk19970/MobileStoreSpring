package com.fsoft.team.controller;

import com.fsoft.team.dtos.Cart;
import com.fsoft.team.entity.OrderEntity;
import com.fsoft.team.entity.OrderDetailEntity;
import com.fsoft.team.entity.ProductEntity;
import com.fsoft.team.entity.UserEntity;
import com.fsoft.team.service.OrderDetailService;
import com.fsoft.team.service.OrderService;
import com.fsoft.team.service.ProductService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"CART", "USER"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductService productService;

    @GetMapping("/checkout")
    public String checkout(@RequestParam("txtTotalPrice") Float totalPrice, Model model) {
        Cart cart = (Cart) model.getAttribute("CART");
        if (!cart.getCart().isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = now.format(formatter);
            LocalDateTime orderDate = LocalDateTime.parse(formatDateTime, formatter);

//            UserEntity user = (UserEntity) model.getAttribute("USER");
            UserEntity user = new UserEntity("lgt", "aaa", "107", "User");
            OrderEntity order = new OrderEntity(user, orderDate, totalPrice);

            OrderEntity newOrder = orderService.insertOrder(order);
            for (ProductEntity product : cart.getCart().values()) {
                int max = productService.checkUnitsInStock(product.getProductID());
                int newUnitsInStock = max - product.getUnitInStock();
                productService.updateUnitsInStock(newUnitsInStock, product.getProductID());

                OrderDetailEntity orderDetail = new OrderDetailEntity(product, product.getUnitInStock(), product.getUnitInStock() * product.getUnitPrice(), newOrder);
                orderDetailService.insertOrderDetail(orderDetail);
            }
            model.addAttribute("THANK", "Thank for your shopping.");
            cart.clear();
            model.addAttribute("CART", cart);
        }
        return "Cart";
    }
}

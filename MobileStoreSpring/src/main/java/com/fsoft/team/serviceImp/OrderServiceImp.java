package com.fsoft.team.serviceImp;

import com.fsoft.team.entity.OrderEntity;
import com.fsoft.team.repository.OrderRepository;
import com.fsoft.team.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderEntity insertOrder(OrderEntity order) {
        return orderRepository.save(order);
    }
}

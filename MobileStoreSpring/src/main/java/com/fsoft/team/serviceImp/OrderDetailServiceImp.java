package com.fsoft.team.serviceImp;

import com.fsoft.team.entity.OrderDetailEntity;
import com.fsoft.team.repository.OrderDetailRepository;
import com.fsoft.team.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImp implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailEntity insertOrderDetail(OrderDetailEntity orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}

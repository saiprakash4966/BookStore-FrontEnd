package com.example.storebackend.service;


import com.example.storebackend.dto.OrderDto;
import com.example.storebackend.model.OrderData;
import com.example.storebackend.model.UserRegistrationData;
import com.example.storebackend.repository.OrderRepository;
import com.example.storebackend.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {


    @Autowired
    UserRegistrationRepository userRepo;

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    BookDetailsService bookService;

    @Override
    public OrderData placeOrder(int userId, OrderDto orderDto) {
        Optional<UserRegistrationData> isPresent = userRepo.findById(userId);
        System.out.println("user: "+isPresent);
        if (isPresent.isPresent()) {
            OrderData order = new OrderData();
            order.crateOrder(orderDto);
            return orderRepo.save(order);
        }
        return null;
    }

    @Override
    public String cancelOrder(int orderId, int userId) {
        Optional<UserRegistrationData> isPresent = userRepo.findById(userId);
        if (isPresent.isPresent()) {
            Optional<OrderData> order = orderRepo.findById(orderId);
            if (order.isPresent()) {
                order.get().setCancel(true);
                orderRepo.save(order.get());
                return "Cancel order Successful";
            }
        }
        return "cancel order not successful";
    }

    @Override
    public List<OrderData> userOrders(int userId) {
        Optional<UserRegistrationData> isPresent = userRepo.findById(userId);
        if (isPresent.isPresent()) {
            List<OrderData> orders = orderRepo.findAllByUserId(userId);
            return orders;
        }
        return null;
    }

    @Override
    public List<OrderData> allOrders() {
        List<OrderData> orders = orderRepo.findAll();
        if (orders.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < orders.size(); i++) {
                int id = orders.get(i).getId();
                Optional<OrderData> orderByOrderId = orderRepo.findById(id);
                if (orderByOrderId.isPresent()) {
                    orderByOrderId.get().setCancel(false);
                    orderRepo.save(orderByOrderId.get());
                    return orders;
                }
            }
        }
        return null;
    }
}

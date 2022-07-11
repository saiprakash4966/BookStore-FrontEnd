package com.example.storebackend.service;


import com.example.storebackend.dto.OrderDto;
import com.example.storebackend.model.OrderData;

import java.util.List;

public interface IOrderService {

    OrderData placeOrder(int userId, OrderDto orderDto);

    String cancelOrder(int orderId,int userId);

    List<OrderData> userOrders(int userId);

    List<OrderData> allOrders();
}

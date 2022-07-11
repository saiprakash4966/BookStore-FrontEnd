package com.example.storebackend.controller;

import com.example.storebackend.dto.OrderDto;
import com.example.storebackend.dto.ResponseDto;
import com.example.storebackend.model.OrderData;
import com.example.storebackend.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping("/placeOrder/{userId}")
    public ResponseEntity<ResponseDto> placeOrder(@PathVariable("userId") int userId, @RequestBody OrderDto orderDto) {
        OrderData order = orderService.placeOrder(userId,orderDto);
        ResponseDto response= new ResponseDto("Order Placed Successfully","Order Details Order ID:" +  order.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/cancelOrder/{orderId}/{userId}")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable int orderId,@PathVariable int userId) {
        String order=orderService.cancelOrder(orderId,userId);
        ResponseDto response= new ResponseDto("id "+orderId,  order);
        return new ResponseEntity<> (response,HttpStatus.ACCEPTED);
    }

    @GetMapping("/userOrders/{userId}")
    public ResponseEntity<ResponseDto> getUserOrders(@PathVariable("userId") int userId){
        List<OrderData> userOrders = orderService.userOrders(userId);
        ResponseDto response = new ResponseDto("Orders of user", userOrders);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/allOrders")
    public ResponseEntity<ResponseDto> getAllOrders(){
        List<OrderData> orders = orderService.allOrders();
        ResponseDto response = new ResponseDto("Orders of user", orders);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}

package com.example.storebackend.model;

import com.example.storebackend.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDate;
    private int totalPrice;
    private int userId;
    private boolean cancel= false;




    public void crateOrder(OrderDto orderDto) {
        this.orderDate = LocalDate.now();
        this.totalPrice = orderDto.getTotalPrice();
        this.userId = orderDto.getUserId();
        this.cancel = false;

    }

}
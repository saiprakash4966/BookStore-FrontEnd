package com.example.storebackend.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CartServiceDto {
    public int userId;
    public int bookId;
    public int quantity;
    public long totalPrice;
}

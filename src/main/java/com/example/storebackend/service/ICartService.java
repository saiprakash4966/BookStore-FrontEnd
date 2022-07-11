package com.example.storebackend.service;

import com.example.storebackend.dto.CartServiceDto;
import com.example.storebackend.model.CartData;

import java.util.List;

public interface ICartService {

    CartData addToCart(CartServiceDto cartDTO);

    void deleteFromCart(int cartId);
    void deleteAll();

    CartData updateQuantity(int cartId, int quantity,long totalPrice);

    List<CartData> findAllInCart(int userId);
}

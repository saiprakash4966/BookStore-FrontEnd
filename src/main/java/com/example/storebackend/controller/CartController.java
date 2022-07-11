package com.example.storebackend.controller;

import com.example.storebackend.dto.CartServiceDto;
import com.example.storebackend.dto.ResponseDto;
import com.example.storebackend.model.CartData;
import com.example.storebackend.repository.CartRepository;
import com.example.storebackend.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    public ICartService cartService;

    @Autowired
    CartRepository cartRepo;

    @PostMapping("/add")
    ResponseEntity<ResponseDto> addToCart(@RequestBody CartServiceDto cartDTO) {
        CartData add = cartService.addToCart(cartDTO);
        ResponseDto response = new ResponseDto("Product Added To Cart ", add);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{cartId}")
    ResponseEntity<ResponseDto> removeFromCart(@PathVariable("cartId") int cartId) {
        cartService.deleteFromCart(cartId);
        ResponseDto response = new ResponseDto("Delete call success for item Removed From Cart ", "deleted id:" + cartId);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

    @DeleteMapping("/removeAll")
    ResponseEntity<ResponseDto> removeAllFromCart() {
        cartService.deleteAll();
        ResponseDto response = new ResponseDto("All Items deleted from cart");
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{cartId}/{quantity}/{totalPrice}")
    ResponseEntity<ResponseDto> updateCart( @PathVariable("cartId") int cartId,
                                            @PathVariable("quantity") int quantity,  @PathVariable("totalPrice") long totalPrice) {

        CartData cart = cartService.updateQuantity( cartId, quantity,totalPrice);
        ResponseDto response = new ResponseDto("Update call success for item Quantity updated From Cart ", cart);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    ResponseEntity<ResponseDto> findAllCartsByUser(@PathVariable("userId") int userId) {
        List<CartData> allItemsForUser = cartService.findAllInCart(userId);
        System.out.println(allItemsForUser);
        ResponseDto response = new ResponseDto("All Items in Cart for user ", allItemsForUser);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<ResponseDto> findAllCarts() {
        Iterable<CartData> allItems = cartRepo.findAll();
        ResponseDto response = new ResponseDto("All Items in Cart ", allItems);
        return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
    }

}
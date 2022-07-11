package com.example.storebackend.repository;

import com.example.storebackend.model.CartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<CartData, Integer> {
    @Query(value = "SELECT * FROM cart where user_Id = :userId", nativeQuery = true)
    List<CartData> findAllCartsByUserId(int userId);

    CartData getById(int cartId);
}

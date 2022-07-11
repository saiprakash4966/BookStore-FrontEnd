package com.example.storebackend.repository;


import com.example.storebackend.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderData, Integer> {

    List<OrderData> findAllByUserId(int id);
}

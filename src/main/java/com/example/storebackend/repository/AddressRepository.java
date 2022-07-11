package com.example.storebackend.repository;

import com.example.storebackend.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    // Optional<UserRegistrationData> findAddressById(int userId);
    @Query(value = "select * from address where user_id= :userId", nativeQuery = true)
    Address findById(int userId);

//    Optional<Address> findById(UserRegistrationData userId);
}

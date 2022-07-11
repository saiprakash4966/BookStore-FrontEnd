package com.example.storebackend.service;

import com.example.storebackend.dto.AddressDto;
import com.example.storebackend.model.Address;

public interface IAddressDetailsService {

    Address getData(int userId);
    Address customerDetails(AddressDto addressDto);
    Address updateCustomerAddress(int userId, AddressDto addressDto);
}
